package com.example.test.home;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeImageServiceImpl implements HomeImageService {

	private final HomeImageRepository homeImageRepository;

	@Value("${file.homeImagePath}")
	private String uploadFolder;

	@Override
	public void upload(HomeImageUploadDTO imageUploadDTO) {
		MultipartFile file = imageUploadDTO.getFile();

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + file.getOriginalFilename();

		File destinationFile = new File(uploadFolder + imageFileName);

		try {
			file.transferTo(destinationFile);
			List<HomeImage> homeImageIsNull = homeImageRepository.findAll();
			HomeImage homeImage;
			if (homeImageIsNull.isEmpty() != true) {
				// 이미지가 이미 존재하면 url 업데이트
				File deletefile = new File("C:/Temp" + homeImageRepository.getReferenceById(1).getUrl());
				if (deletefile.exists() == false)
					deletefile = new File("home/lazy/Temp" + homeImageRepository.getReferenceById(1).getUrl());
				deletefile.delete();
				homeImage = homeImageRepository.getReferenceById(1);
				homeImage.updateUrl("/homeImage/" + imageFileName);

			} else {
//			 이미지가 없으면 객체 생성 후 저장
				homeImage = HomeImage.builder().url("/homeImage/" + imageFileName).build();

			}
			homeImageRepository.save(homeImage);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HomeImageResponseDTO getImageDTO() {
		String defaultImageUrl = "/homeImage/backgroundimg.jpg";
		try {
			HomeImage image = homeImageRepository.getReferenceById(1);

			if (image == null) {
				return HomeImageResponseDTO.builder().url(defaultImageUrl).build();
			} else {
				return HomeImageResponseDTO.builder().url(image.getUrl()).build();
			}

		} catch (Exception e) {

			return HomeImageResponseDTO.builder().url(defaultImageUrl).build();
		}

	}
}
