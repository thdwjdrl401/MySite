package com.example.test.file;

import java.time.LocalDateTime;
import java.util.List;

import com.example.test.bucketlist.BucketList;
import com.example.test.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "boardImage")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "BUCKET_ID")
	private BucketList bucketlist;
	
	private String filename; // 파일 이름
    private String filepath; // 파일이 저장된 경로
}
