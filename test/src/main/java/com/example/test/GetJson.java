package com.example.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetJson {

	public List<String> readJson(String filePath) throws Exception {

		String str = "";
		String[] sb;
		List<String> result_kr = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		ClassPathResource resource = null;
		InputStreamReader reader = null;

		try {

			resource = new ClassPathResource("static/json/" + filePath + ".json");
			reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(reader);

			log.info("resource = " + resource);

			log.info("reader = " + reader);
			log.info("br = " + br);

			while ((str = br.readLine()) != null) {
				if (str.indexOf("SIG_KOR_NM") != -1) {
					sb = str.split("\"");
					result_kr.add(sb[3]);
//									System.out.println(sb[3]); 

				}
				if (str.indexOf("SIG_ENG_NM") != -1) {
					sb = str.split("\"");
					result.add(sb[3]);
//									System.out.println(sb[3]); 

				}
			}
			log.info("result = " + result);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result;

	}
	
	
	
	public List<String> readJson_kr(String filePath) throws Exception {

		String str = "";
		String[] sb;
		List<String> result_kr = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		ClassPathResource resource = null;
		InputStreamReader reader = null;

		try {

			resource = new ClassPathResource("static/json/" + filePath + ".json");
			reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(reader);

			log.info("resource = " + resource);

			log.info("reader = " + reader);
			log.info("br = " + br);

			while ((str = br.readLine()) != null) {
				if (str.indexOf("SIG_KOR_NM") != -1) {
					sb = str.split("\"");
					result_kr.add(sb[3]);
//									System.out.println(sb[3]); 

				}
				if (str.indexOf("SIG_ENG_NM") != -1) {
					sb = str.split("\"");
					result.add(sb[3]);
//									System.out.println(sb[3]); 

				}
			}
			log.info("result = " + result);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result_kr;

	}
	
	
}
