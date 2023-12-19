package com.example.test.home;



public interface HomeImageService {
	/**
	 * 프로필 사진 upload
	 * 
	 * @param imageUploadDTO file
	 * @param email          유저 정보
	 */
	void upload(HomeImageUploadDTO imageUploadDTO);

	/**
	 * 이미지 url 조회
	 * 
	 * @param email 유저 정보
	 * @return 이미지 url
	 */
	HomeImageResponseDTO getImageDTO();
}
