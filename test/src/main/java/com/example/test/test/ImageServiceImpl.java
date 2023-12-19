package com.example.test.test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.user.SiteUser;
import com.example.test.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;
	private final UserRepository memberRepository;

	@Value("${file.profileImagePath}")
	private String uploadFolder;

	@Override
	public void upload(ImageUploadDTO imageUploadDTO, String name) {
		SiteUser member = memberRepository.findByusername(name)
				.orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
		MultipartFile file = imageUploadDTO.getFile();

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + file.getOriginalFilename();

		File destinationFile = new File(uploadFolder + imageFileName);

		try {
			file.transferTo(destinationFile);

			Image image = imageRepository.findByUser(member);
			if (image != null) {
				// 이미지가 이미 존재하면 url 업데이트
				File deletefile = new File("C:/Temp" + imageRepository.findByUser(member).getUrl());
				if (deletefile.exists() == false)
					deletefile = new File("home/lazy/Temp" + imageRepository.findByUser(member).getUrl());
				deletefile.delete();
				image.updateUrl("/profileImages/" + imageFileName);
			} else {
				// 이미지가 없으면 객체 생성 후 저장
				image = Image.builder().user(member).url("/profileImages/" + imageFileName).build();
			}
			imageRepository.save(image);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ImageResponseDTO findImage(String name) {
		SiteUser member = memberRepository.findByusername(name)
				.orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
		Image image = imageRepository.findByUser(member);

		String defaultImageUrl = "/profileImages/anonymous.png";

		if (image == null) {
			return ImageResponseDTO.builder().url(defaultImageUrl).build();
		} else {
			return ImageResponseDTO.builder().url(image.getUrl()).build();
		}
	}

}