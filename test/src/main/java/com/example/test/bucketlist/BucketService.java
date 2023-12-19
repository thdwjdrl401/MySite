package com.example.test.bucketlist;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.DataNotFoundException;
import com.example.test.test.ImageRepository;
import com.example.test.user.SiteUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BucketService {
	private final BucketRepository bucketRepository;
	private final BucketImageRepository bucketImageRepository;

	@Value("${file.boardImagePath}")
	private String uploadFolder;

	public List<BucketList> getList() {
		return this.bucketRepository.findAll();
	}

	public BucketList getBucket(Integer id) {
		Optional<BucketList> bucket = this.bucketRepository.findById(id);
		if (bucket.isPresent()) {
			return bucket.get();
		} else {
			throw new DataNotFoundException("bucketlist not found");
		}
	}

	public BucketResponseDTO getBucketDTO(Integer id) {
		BucketList bucketList = bucketRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
		BucketResponseDTO result = BucketResponseDTO.builder().bucketList(bucketList).build();
		return result;
	}

	public void create(String subject, String content, BucketImageUploadDTO bucketImageUploadDTO, SiteUser user) {
		BucketList b = new BucketList();
		b.setSubject(subject);
		b.setContent(content);
		b.setCreateDate(LocalDateTime.now());
		b.setAuthor(user);

		this.bucketRepository.save(b);
		if (bucketImageUploadDTO.getFiles() != null && !bucketImageUploadDTO.getFiles().isEmpty()) {
			for (MultipartFile file : bucketImageUploadDTO.getFiles()) {
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles().isEmpty()));
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles()));
//				log.info(String.valueOf(file.getSize()));
//				log.info(String.valueOf(file.getName()));

				if (file.getSize() != 0) {

					UUID uuid = UUID.randomUUID();
					String imageFileName = uuid + "_" + file.getOriginalFilename();

					File destinationFile = new File(uploadFolder + imageFileName);

					try {
						file.transferTo(destinationFile);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

					BucketImage image = BucketImage.builder().url("/boardImages/" + imageFileName).bucketList(b)
							.build();
					bucketImageRepository.save(image);
				}
			}
		}

	}

	public void modify(BucketList bucketList, String subject, String content,
			BucketImageUploadDTO bucketImageUploadDTO) {
		bucketList.setSubject(subject);
		bucketList.setContent(content);
		if (bucketImageUploadDTO.getFiles() != null && !bucketImageUploadDTO.getFiles().isEmpty()) {
			for (MultipartFile file : bucketImageUploadDTO.getFiles()) {
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles().isEmpty()));
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles()));
//				log.info(String.valueOf(file.getSize()));
//				log.info(String.valueOf(file.getName()));

				if (file.getSize() != 0) {

					UUID uuid = UUID.randomUUID();
					String imageFileName = uuid + "_" + file.getOriginalFilename();

					File destinationFile = new File(uploadFolder + imageFileName);

					try {
						file.transferTo(destinationFile);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

					BucketImage image = BucketImage.builder().url("/boardImages/" + imageFileName)
							.bucketList(bucketList).build();
					bucketImageRepository.save(image);
				}
			}
		}
		this.bucketRepository.save(bucketList);
	}

	public void delete(BucketList bucketList, Integer id) {
		BucketList bucketList2 = bucketRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
		BucketResponseDTO result = BucketResponseDTO.builder().bucketList(bucketList2).build();

		List<String> delete_URL = result.getBucketImages();
		for (int i = 0; i < delete_URL.size(); i++) {
			File deletefile = new File("C:/Temp" + delete_URL.get(i));
			if (deletefile.exists() == false)
				deletefile = new File("home/lazy/Temp" + delete_URL.get(i));
			deletefile.delete();
		}

		this.bucketRepository.delete(bucketList);
	}

	public void clear(BucketList bucketList) {

		if (bucketList.getClear() == 1) {
			bucketList.setClear(0);
			bucketList.setClearDate(null);
		} else if (bucketList.getClear() == 0) {
			bucketList.setClear(1);
			bucketList.setClearDate(LocalDateTime.now());
		}

		this.bucketRepository.save(bucketList);
	}

	public void clear2(BucketList bucketList, String subject, String content,
			BucketImageUploadDTO bucketImageUploadDTO) {

		if (bucketList.getClear() == 1) {
			bucketList.setClear(0);
			bucketList.setClearDate(null);
		} else if (bucketList.getClear() == 0) {
			bucketList.setClear(1);
			bucketList.setClearDate(LocalDateTime.now());
		}

		bucketList.setSubject(subject);
		bucketList.setContent(content);

		if (bucketImageUploadDTO.getFiles() != null && !bucketImageUploadDTO.getFiles().isEmpty()) {
			for (MultipartFile file : bucketImageUploadDTO.getFiles()) {
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles().isEmpty()));
//				log.info(String.valueOf(bucketImageUploadDTO.getFiles()));
//				log.info(String.valueOf(file.getSize()));
//				log.info(String.valueOf(file.getName()));

				if (file.getSize() != 0) {

					UUID uuid = UUID.randomUUID();
					String imageFileName = uuid + "_" + file.getOriginalFilename();

					File destinationFile = new File(uploadFolder + imageFileName);

					try {
						file.transferTo(destinationFile);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

					BucketImage image = BucketImage.builder().url("/boardImages/" + imageFileName)
							.bucketList(bucketList).build();
					bucketImageRepository.save(image);
				}
			}
		}
		this.bucketRepository.save(bucketList);
	}

	public void deleteImage(String url) {
		File deletefile = new File("C:/Temp" + bucketImageRepository.findByUrl(url).getUrl());
		if (deletefile.exists() == false)
			deletefile = new File("home/lazy/Temp" + bucketImageRepository.findByUrl(url).getUrl());
		deletefile.delete();
		log.info("C:/Temp" + bucketImageRepository.findByUrl(url).getUrl());

		String f;
		if (deletefile.exists() == true)
			f = "true";
		else
			f = "false";
		log.info("C:/Temp" + bucketImageRepository.findByUrl(url).getUrl());

		log.info(f);
		if (bucketImageRepository.findByUrl(url) != null) {
			bucketImageRepository.deleteById(bucketImageRepository.findByUrl(url).getId());
		}
	}

}
