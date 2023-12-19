package com.example.test.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.user.SiteUser;

public interface ImageRepository extends JpaRepository<Image, Long> {

	Image findByUser(SiteUser user);
	

}