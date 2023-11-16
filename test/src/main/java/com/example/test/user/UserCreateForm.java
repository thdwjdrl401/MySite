package com.example.test.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사  용  자 ID는       필  수  항  목  입  니  다 .")
	private String username;
	@NotEmpty(message = "사  용  자  이  름  은       필  수  항  목  입  니  다 .")
	private String name;
	@NotEmpty(message = "비  밀  번  호  는       필  수  항  목  입  니  다 .")
	private String password1;
	@NotEmpty(message = "비  밀  번  호       확  인  은       필  수  항  목  입  니  다 .")
	private String password2;
	@NotEmpty(message = "이  메  일  은       필  수  항  목  입  니  다 .")
	@Email
	private String email;
}