package com.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	
	@GetMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("message","Sameer");
		return "index";
	}
	
	
	@GetMapping("/addbootstrap")
	public String hellobootstrap(Model model) {
		
		model.addAttribute("message","Sameer");
		return "addbootstrap";
	}

}
