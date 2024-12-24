package com.app.service;

import java.util.List;

import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;

public interface EnquiryService {

	Enquiry editEnquiry(int customerID, Enquiry enquiryDetails);
	Enquiry updateEnquiry(int customerID, Enquiry enquiryDetails);
	Boolean deleteEnquiry(Integer customerID);
	Enquiry saveEnquiry(Enquiry enquiry);
	List<Enquiry> getAllEnquiry();
	Enquiry addData(Enquiry enquiry);
	Enquiry getEnquiry(Integer customerID);
    EnquiryResponseDTO getEnquiryDetails(Integer customerID);
	List<Enquiry> findEnquiriesByStatus(String status);
	
	Enquiry updateEnquiry(int customerID, String status);
	
	
	

}
