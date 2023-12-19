package com.example.test.bucketlist;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.test.ImageServiceImpl;
import com.example.test.user.SiteUser;
import com.example.test.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/bucket")
@RequiredArgsConstructor
@Controller
public class BucketController {
	private final BucketService bucketService;
	private final UserService userService;

	@GetMapping("/list")
	public String list(Model model) {
		List<BucketList> bucketList = this.bucketService.getList();
		model.addAttribute("bucketList", bucketList);
		return "bucket_list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String bucketCreate(BucketForm bucketForm) {
		return "bucket_form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String bucketCreate(BucketForm bucketForm, BindingResult bindingResult,
			@ModelAttribute BucketImageUploadDTO bucketImageUploadDTO, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "bucket_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.bucketService.create(bucketForm.getSubject(), bucketForm.getContent(), bucketImageUploadDTO, siteUser);
		return "redirect:/bucket/list";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		BucketList bucketList = this.bucketService.getBucket(id);
		BucketResponseDTO result = bucketService.getBucketDTO(id);
		model.addAttribute("dto", result);
		model.addAttribute("bucketList", bucketList);
		return "bucket_detail";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String bucketModify(Model model, BucketForm bucketForm, @PathVariable("id") Integer id,
			Principal principal) {
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수  정  권  한  이  없  습  니  다 .");
		}
		BucketResponseDTO result = bucketService.getBucketDTO(id);
		model.addAttribute("dto", result);
		bucketForm.setSubject(bucketList.getSubject());
		bucketForm.setContent(bucketList.getContent());
		return "bucket_modify";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String bucketModify(@ModelAttribute BucketImageUploadDTO bucketImageUploadDTO, @Valid BucketForm bucketForm,
			BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
		if (bindingResult.hasErrors()) {
			return "bucket_modify";
		}
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수  정  권  한  이  없  습  니  다 .");
		}
		this.bucketService.modify(bucketList, bucketForm.getSubject(), bucketForm.getContent(), bucketImageUploadDTO);
		return String.format("redirect:/bucket/detail/%s", id);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String bucketDelete(Principal principal, @PathVariable("id") Integer id) {
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭  제  권  한  이 없  습  니  다 .");
		}
		this.bucketService.delete(bucketList, id);
		return "redirect:/bucket/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/clear/{id}")
	public String bucketC(Principal principal, @PathVariable("id") Integer id) {
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "완  료  권  한  이 없  습  니  다 .");
		}
		this.bucketService.clear(bucketList);
		return "redirect:/bucket/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/clear2/{id}")
	public String bucketClear(BucketForm bucketForm, @PathVariable("id") Integer id, Principal principal) {
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "완  료  권  한  이  없  습  니  다 .");
		}
		if (bucketList.getClear() == 1) {
			this.bucketService.clear(bucketList);
			return String.format("redirect:/bucket/detail/%s", id);
		} else {
			bucketForm.setSubject(bucketList.getSubject());
			bucketForm.setContent(bucketList.getContent());
			return "bucket_clear";
		}

	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/clear2/{id}")
	public String bucketClear(@Valid BucketForm bucketForm, @ModelAttribute BucketImageUploadDTO bucketImageUploadDTO,
			BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
		if (bindingResult.hasErrors()) {
			return "bucket_clear";
		}
		BucketList bucketList = this.bucketService.getBucket(id);
		if (!bucketList.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "완  료  권  한  이  없  습  니  다 .");
		}
		this.bucketService.clear2(bucketList, bucketList.getSubject(), bucketList.getContent(), bucketImageUploadDTO);
		return "redirect:/bucket/list";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteimage")
	@ResponseBody
	public String deleteImage(@RequestParam(required = false, value = "image_url") String image_url,
			@RequestParam(required = false, value = "current_url") String current_url, Principal principal) {
		log.info("image_url" + image_url);
		log.info("current_url" + current_url);

		this.bucketService.deleteImage(image_url);
		String[] redirect_url = current_url.split("/");
		log.info("5 = " + redirect_url[5]);

		return "redirect:/bucket/modify/" + redirect_url[5];
	}
}
