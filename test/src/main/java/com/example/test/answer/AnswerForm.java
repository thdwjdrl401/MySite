package com.example.test.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	@NotEmpty(message = "내  용  은       필  수  항  목  입  니  다 .") 
	private String content;
}
