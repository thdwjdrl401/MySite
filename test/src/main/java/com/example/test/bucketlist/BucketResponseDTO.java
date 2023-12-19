package com.example.test.bucketlist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.test.user.SiteUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BucketResponseDTO {
	private Integer id;
	private String subject;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime clearDate;
	private SiteUser author;
	private Integer clear = 0;
	private List<String> bucketImages;
	
	@Builder
    public BucketResponseDTO(BucketList bucketList) {
        this.id = bucketList.getId();
        this.subject = bucketList.getSubject();
        this.content = bucketList.getContent();
        this.createDate = bucketList.getCreateDate();
        this.author = bucketList.getAuthor();
        this.clear = bucketList.getClear();
        this.bucketImages = bucketList.getBucketImages().stream()
        		.map(BucketImage::getUrl).collect(Collectors.toList());
    }
}
