package com.example.test.map;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.home.HomeImageUploadDTO;

import lombok.Data;

@Data

public class MapImageUploadDTO {
	private MultipartFile file;

}
