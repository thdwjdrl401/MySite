package com.example.test.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageUploadDTO {

    private List<MultipartFile> files;
}