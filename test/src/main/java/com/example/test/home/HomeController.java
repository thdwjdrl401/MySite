package com.example.test.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
	private final HomeImageService HomeImageService;

	@GetMapping("/index")
	public String index(Model model) {
		HomeImageResponseDTO result = HomeImageService.getImageDTO();
		model.addAttribute("dto", result);
		return "index";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/changehome")
	public String changehome(Model model) {
		HomeImageResponseDTO result = HomeImageService.getImageDTO();
		model.addAttribute("dto", result);
		return "changehome";
	}
	
	@PostMapping("/upload")
	public String upload(@ModelAttribute HomeImageUploadDTO imageUploadDTO) {
		HomeImageService.upload(imageUploadDTO);

		return "redirect:/";
	}

}