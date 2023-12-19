package com.example.test.bucketlist;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BucketImageUploadDTO {
	private List<MultipartFile> files;
}
