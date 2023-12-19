package com.example.test.test;

import lombok.Data;

import java.util.List;

@Data
public class Content {

	private int id;
	private String title;
	private String texts;

	private String writer;
	private String password;

	private String updateDate;

	private UploadFile attachFile; // 첨부 파일
	private List<UploadFile> imageFiles; // 첨부 이미지
}