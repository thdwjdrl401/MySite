package com.example.test.map;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MapImageResponseDTO {

	private String url;
	private String location;
	private String location_kr;


	@Builder
	public MapImageResponseDTO(MapImage mapImage) {
		this.url = mapImage.getUrl();
		
		this.location = mapImage.getLocation();
		this.location_kr = mapImage.getLocation_kr();

	}

}
