package org.mql.controllers.dashboard;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Vector;

import javax.validation.Valid;

import org.mql.dao.FormationRepository;
import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.models.Category;
import org.mql.models.File;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.models.Module;
import org.mql.models.Streaming;
import org.mql.services.CategoryService;
import org.mql.services.FormationService;
import org.mql.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dashboard")
public class DashFormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService memberService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FormationService formationService;
	
	private static String uploadDir = System.getProperty("user.dir")+ "/src/main/resources/static/";
	
	@GetMapping("/")
	public String mainPage(Model model, Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		model.addAttribute("member", member);
		return "dashboard/index";
	}
	
	@GetMapping("/formation")
	public String getFormations(Model model,Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		List<Formation> formations = formationService.findByResponsable(member);
		model.addAttribute("formations", formations);
		return "dashboard/formations";
	}
	

	//CODE HAJAR : ajouter une formation via une formulaire**************************************************
	@GetMapping(value="formation/add")
	public String FormulaireFormation(Model model)
	{
		List<Category> categories = categoryService.findAll();
		model.addAttribute("formation",new Formation());
		model.addAttribute("member",new Member());
		model.addAttribute("categories",categories);
		return "dashboard/addFormation" ;
	}
	
	//affichage de la formation ajoutee**********************************************************************
	@PostMapping(value="/saveFormation")
	public String save(Model model,@Valid Formation formation,@RequestParam("category_id") int categoryId ,Member member, BindingResult bindingResult,Principal principal)
	{
		if(bindingResult.hasErrors())
		{
			return "/dashboard/formation/add" ;
		}
		
		member=memberService.findByEmail(principal.getName());
		Category cat = categoryService.findById(categoryId);
		formation.setCreator(member);
		formation.setCategory(cat);
		formationRepository.save(formation);
		
		return "redirect:/dashboard/formation/";
	}
	
	
	
	@GetMapping("/formation/{id}/add")
	public String ModuleForm(@PathVariable int id,Model model) {
		Formation formation = formationRepository.findById(id).get();
		List<Member> members = memberService.findTeachers();
		System.out.println(members);
		model.addAttribute("members", members);
		model.addAttribute("formation", formation);
		model.addAttribute("module",new Module());
		return "/dashboard/addmodule";
	}
	
	@PostMapping("addModule")
	public String addModule(@ModelAttribute Module module,@RequestParam("formation_id") int id,@RequestParam("member_id") int memberId,Model model,@RequestParam MultipartFile[] supportsEdu) {
		Formation formation = formationRepository.findById(id).get();
		Member member = memberRepository.findById(memberId).get();
		module.setTeacher(member);
		for (MultipartFile supportEdu : supportsEdu) {
			String fileName = uploadFile("support/", supportEdu);
			File file = new File(fileName);
			file.setModule(module);
			module.addFile(file);
		}
		formation.add(module);
		formationRepository.save(formation);
		return "redirect:/dashboard/formation/";
	}
	
	// Get the authetificated student followed Formations
	@GetMapping("/followedFormation")
	public String getFollowedFormations(Model model,Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		List<Formation> formations = formationService.findByFollower(member);
		model.addAttribute("formations", formations);
		return "dashboard/followedFormations";
	}
	
	private String uploadFile(String path,MultipartFile file) {
		System.out.println(uploadDir);
		System.out.println(path);
		System.out.println(uploadDir + path);
		
		String name = file.getOriginalFilename();
		Path fileNameAndPath = Paths.get(uploadDir + path, name);
		try {
			Files.write(fileNameAndPath, file.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*****************" + name);
		return name;
	}
	

	/***************************************************************************************************/
	// Get the authetificated student Streamed Courses
	@GetMapping("/StreamedCourses")
	public String getStreamedCourses(Model model,Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		List<Formation> formations = formationService.findByFollower(member);
		List<Module> modules = new Vector<>();
		List<Streaming> streamings = new Vector<>();
		for (Formation formation:formations ) {
			modules.addAll(formation.getModules());
		}
		for (Module module: modules ) {
			streamings.addAll(module.getStreams());
		}
		model.addAttribute("streams", streamings);
		return "dashboard/StreamedCourses";
	}
	/***************************************************************************************************/

	
}
