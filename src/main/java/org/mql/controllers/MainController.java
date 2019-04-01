package org.mql.controllers;

import java.util.List;

import org.mql.dao.CommentRepository;
import org.mql.models.Category;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.services.CategoryService;
import org.mql.services.FormationService;
import org.mql.services.MemberService;
import org.mql.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	StreamingService streamingService;
	
	@Autowired 
	FormationService formationService;
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping(path = "/security")
	public String addNewMember() {
		return "dashboard/index";
	}

	/****************** added *///////////////////
	@GetMapping("/teacher/{id}")
	public String showTeacher(@PathVariable int id, Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);
		return "main_views/teacher-detail";
	}

	//
	@GetMapping("/")
	public String home(Model model) {
		List<Formation> formations = formationService.findTop6ByOrderByIdDesc();
		model.addAttribute("formations",formations);
		List<Category> categories = categoryService.findTop6();
		model.addAttribute("categories",categories);
		return "main_views/home";
	}
	
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		// last modifs
		Formation formation = new Formation();
		
		Category cat = categoryService.findById(1);
		formation.setCategory(cat);
		formationService.save(formation);
		return "saved";
	}

	@GetMapping("/articles")
	public String articles() {
		return "main_views/articles";
	}

	@GetMapping("/contacts")
	public String contacts() {
		return "main_views/contacts";
	}

	@GetMapping("/register2")
	public String register() {
		return "main_views/register";
	}


}
