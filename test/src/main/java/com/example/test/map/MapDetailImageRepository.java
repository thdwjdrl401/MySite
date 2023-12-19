package com.example.test.map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MapDetailImageRepository  extends JpaRepository<MapDetailImage, Integer>{
	Optional<MapDetailImage> findById(Integer id);
	MapDetailImage findByLocation(String location);
}
