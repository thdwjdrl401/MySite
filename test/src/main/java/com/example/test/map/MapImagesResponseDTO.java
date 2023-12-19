package com.example.test.map;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MapImagesResponseDTO {
	private List<String> urls;

	private List<String> locations;
	
	@Builder
	public MapImagesResponseDTO(List<MapImage> mapImages) {
		this.urls = mapImages.stream().map(MapImage::getUrl).collect(Collectors.toList());
		this.locations = mapImages.stream().map(MapImage::getLocation).collect(Collectors.toList());
	}
}
