package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.MailDetailsDto;
import com.app.entity.Enquiry;
import com.app.service.CommunicationService;

@Service
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	private RestTemplate template;

	@Override
	public void sendWelcomeMail(Enquiry saveEnquiry) {
		MailDetailsDto mailDto = new MailDetailsDto();

		mailDto.setToMail(saveEnquiry.getEmail());
		mailDto.setSubject("Unified Finance.");
		mailDto.setMailContent("Hello " + saveEnquiry.getFirstName() + saveEnquiry.getLastName() + "\n"
				+ "Welcome To Unified Finance Bank , Your Request has been Submitted..!!!!! ");

		String mailFeedback = template.postForObject("http://localhost:9092/mail/send-simple-mail", mailDto, String.class);
		
//		if(!mailFeedback.equals("Mail Sent"))
//		{
//			
//		}
				
	}

}
