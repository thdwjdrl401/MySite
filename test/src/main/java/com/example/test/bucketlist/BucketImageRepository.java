package com.example.test.bucketlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketImageRepository extends JpaRepository<BucketImage, Long> {
	BucketImage findByBucketList(BucketList bucketList);
	BucketImage findByUrl(String url);
	BucketImage deleteByUrl(String url);

}
