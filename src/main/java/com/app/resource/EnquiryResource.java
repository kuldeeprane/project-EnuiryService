package com.app.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.CibilRequestDTO;
import com.app.dto.EnquiryRequestDTO;
import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Cibil;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;
import com.app.service.CibilService;
import com.app.service.EnquiryService;

@Component
public class EnquiryResource {
	@Autowired
	private EnquiryService enquiryService;
	@Autowired
	private CibilService cibilService;
	@Autowired
	private ModelMapper modelMapper;

	public EnquiryResponseDTO saveEnquiry(EnquiryRequestDTO enquiryRequestDTO) {

		Enquiry enquiry = modelMapper.map(enquiryRequestDTO, Enquiry.class);

		enquiry.setStatus(EnquiryStatus.REGISTER);

		Enquiry saveEnquiry = enquiryService.saveEnquiry(enquiry);
		EnquiryResponseDTO saveEnquiryResponseDTO = modelMapper.map(saveEnquiry, EnquiryResponseDTO.class);
		return saveEnquiryResponseDTO;

	}

	public void generatedRandomCibilScore(CibilRequestDTO cibilRequestDTO) {

		Cibil cibil = modelMapper.map(cibilRequestDTO, Cibil.class);

		Cibil savedCibil = cibilService.generateRandomCibilScore(cibil);

		Enquiry exitingEnquiry = enquiryService.getEnquiry(cibilRequestDTO.getCustomerID());

		System.out.println(cibilRequestDTO.getCustomerID());
		exitingEnquiry.setCibil(savedCibil);
		enquiryService.saveEnquiry(exitingEnquiry);
	}

}
