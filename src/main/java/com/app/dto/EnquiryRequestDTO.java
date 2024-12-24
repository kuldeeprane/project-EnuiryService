package com.app.dto;
import com.app.entity.EnquiryStatus;

import lombok.Data;

@Data
public class EnquiryRequestDTO {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Long mobileNo;
	private String pancardNo;
	private EnquiryStatus status;

}
