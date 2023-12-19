package com.example.test.map;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class MapDetailImagesResponseDTO {
	private List<String> urls;

	private List<String> locations;
	
	@Builder
	public MapDetailImagesResponseDTO(List<MapDetailImage> mapDetailImage) {
		this.urls = mapDetailImage.stream().map(MapDetailImage::getUrl).collect(Collectors.toList());
		this.locations = mapDetailImage.stream().map(MapDetailImage::getLocation).collect(Collectors.toList());
	}
}
