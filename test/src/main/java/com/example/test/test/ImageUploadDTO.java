package com.example.test.test;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDTO {

	private MultipartFile file;
}