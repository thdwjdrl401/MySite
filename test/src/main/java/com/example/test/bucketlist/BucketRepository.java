package com.example.test.bucketlist;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.user.SiteUser;

public interface BucketRepository extends JpaRepository<BucketList, Integer>  {

}
