package com.example.test.home;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeImageRepository extends JpaRepository<HomeImage, Integer> {
	Optional<HomeImage> findById(Integer id);
//	HomeImage findByImg(String img);
//	void save(Optional<HomeImage> homeImage);
//	@Query("insert into HomeImage values (1, '/homeImage/backgroundimg.jpg')")
//	void getInit();
}
