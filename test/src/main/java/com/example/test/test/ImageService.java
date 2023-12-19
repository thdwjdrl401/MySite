package com.example.test.test;

public interface  ImageService {
	/**
	 * 프로필 사진 upload
	 * 
	 * @param imageUploadDTO file
	 * @param email          유저 정보
	 */
	void upload(ImageUploadDTO imageUploadDTO, String email);

	/**
	 * 이미지 url 조회
	 * 
	 * @param email 유저 정보
	 * @return 이미지 url
	 */
	ImageResponseDTO findImage(String email);
}
