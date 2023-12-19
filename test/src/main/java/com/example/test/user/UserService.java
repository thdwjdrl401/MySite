package com.example.test.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.test.DataNotFoundException;
import com.example.test.test.Image;
import com.example.test.test.ImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ImageRepository imageRepository;

	public SiteUser create(String username, String name, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);

		Image image = Image.builder().url("/profileImages/anonymous.png").user(user).build();
		imageRepository.save(image);
		return user;
	}

	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
		if (siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
