package org.mql.controllers;

import java.util.Date;
import java.util.Iterator;

import org.mql.dao.CommentRepository;
import org.mql.models.Comment;
import org.mql.models.Member;
import org.mql.models.Streaming;
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
	CommentRepository commentRepo;

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
	public String home() {
		return "main_views/home";
	}
	
	
	@GetMapping("/test")
	public String test() {
		return "main_views/home";
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
