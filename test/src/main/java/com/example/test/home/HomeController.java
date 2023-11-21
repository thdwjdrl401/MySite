package com.example.test.home;

import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.test.question.Question;

@Controller
public class HomeController {
	@GetMapping("/home/index") 
	public String index() {
		return "index";
		}

}
