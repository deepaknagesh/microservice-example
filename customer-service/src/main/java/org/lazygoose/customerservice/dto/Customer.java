package org.lazygoose.customerservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;

@Data
public class Customer {

	@NonNull
	@NotBlank
	private String name;
	
	@NonNull
	@NotBlank
	private Integer age;
}
