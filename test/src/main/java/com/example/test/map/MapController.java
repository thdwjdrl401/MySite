package com.example.test.map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {
	private final MapImageService MapImageService;

	@GetMapping("")
	public String index(Model model) {
		MapImagesResponseDTO result = MapImageService.getImageDTO();
		model.addAttribute("dto", result);
		return "map";
	}

	@GetMapping(value = "/modify/{location}")
	public String modifyImage(Model model, @PathVariable("location") String location) {
		MapImageResponseDTO result = MapImageService.getImageDTO(location);
//		model.addAttribute("comment", question);
		model.addAttribute("dto", result);
		return "map_modify";

	}

	@PostMapping("/modify/{location}")
	public String modifyImage(@ModelAttribute MapImageUploadDTO imageUploadDTO,
			@PathVariable("location") String location) {

		MapImageService.upload(imageUploadDTO, location);
		return String.format("redirect:/map");
	}

	@GetMapping("/{location}")
	public String mapDetail(Model model, @PathVariable("location") String location) throws Exception {
		MapDetailImagesResponseDTO result = MapImageService.getDetailImageDTO(location);
		
		model.addAttribute("dto", result);
		model.addAttribute("location", location);

		return "map_detail";
	}
	
	@GetMapping(value = "/detail/modify/{location}")
	public String modifyDetailImage(Model model, @PathVariable("location") String location) {
		MapDetailImageResponseDTO result = MapImageService.getDetailIDetailmageDTO(location);
//		model.addAttribute("comment", question);
		model.addAttribute("dto", result);
		return "map_modify";

	}
	@PostMapping("/detail/modify/{location}")
	public String modifyDetailImage(HttpServletRequest request, @ModelAttribute MapImageUploadDTO imageUploadDTO,
			@PathVariable("location") String location) {

		MapImageService.uploadDetail(imageUploadDTO, location);
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}
}
