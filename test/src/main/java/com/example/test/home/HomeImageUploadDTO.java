package com.example.test.home;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.test.ImageUploadDTO;

import lombok.Data;

@Data

public class HomeImageUploadDTO {
	private MultipartFile file;

}
