package com.example.test.home;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomeImageResponseDTO {
	private String url;

	@Builder
	public HomeImageResponseDTO(String url) {
		this.url = url;
	}
}
