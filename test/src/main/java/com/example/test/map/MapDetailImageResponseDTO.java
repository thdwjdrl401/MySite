package com.example.test.map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class MapDetailImageResponseDTO {
	private String url;
	private String location;
	private String location_kr;
	
	@Builder
	public MapDetailImageResponseDTO(MapDetailImage mapDetailImage) {
		this.url = mapDetailImage.getUrl();
		
		this.location = mapDetailImage.getLocation();
		this.location_kr = mapDetailImage.getLocation_kr();

	}
}
