package com.example.test.domain.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 10)
	private String name;

	@Min(0)
	@Max(120)
	private Integer age = 20;

}