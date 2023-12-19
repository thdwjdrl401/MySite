package com.example.test.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.bucketlist.BucketList;

public class ImageService {
//	@Autowired
//	private ImageRepository imageRepository;

	public void uploadFile(MultipartFile file)throws IOException{
		file.transferTo(new File("D:\\"+file.getOriginalFilename()));
	}
}