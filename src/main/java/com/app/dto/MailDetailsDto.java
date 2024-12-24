package com.app.dto;

import lombok.Data;

@Data
public class MailDetailsDto {

	private String toMail;
	private String subject;
	private String mailContent;
}
