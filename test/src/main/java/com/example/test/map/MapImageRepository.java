package com.example.test.map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.home.HomeImage;


public interface MapImageRepository extends JpaRepository<MapImage, Integer>{
	Optional<MapImage> findById(Integer id);
	MapImage findByLocation(String location);
}
