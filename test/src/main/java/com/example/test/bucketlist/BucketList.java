package com.example.test.bucketlist;

import java.time.LocalDateTime;
import java.util.List;

import com.example.test.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bucketList")
public class BucketList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 200)
	private String subject;
	@Column(columnDefinition = "TEXT")
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime clearDate;
	@ManyToOne
	private SiteUser author;
	@Column(length = 1)
	private Integer clear = 0;

	@OneToMany(mappedBy = "bucketList", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<BucketImage> bucketImages;

//	private UploadFile attachFile;          // 첨부 파일
//	private List<UploadFile> imageFiles;    // 첨부 이미지
//	@OneToMany(mappedBy = "bucketList", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//	@OrderBy("id asc")
//	private List<Image> Images;

}
