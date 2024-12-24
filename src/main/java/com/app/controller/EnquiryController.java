package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EnquiryRequestDTO;
import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;
import com.app.exception.EnquiryNotFoundException;
import com.app.resource.EnquiryResource;
import com.app.service.EnquiryService;

import java.util.List;

@RestController
@RequestMapping(value = "/enquiry")

public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	private EnquiryResource enquiryResource;

	public EnquiryController(EnquiryResource enquiryResource) {
		super();
		this.enquiryResource = enquiryResource;
	}

	@PutMapping(value = "/edit-enquiry/{customerID}")
	public ResponseEntity<Enquiry> editEnquiry(@PathVariable int customerID, @RequestBody Enquiry enquiryDetails)
			throws EnquiryNotFoundException {

		Enquiry editedData = enquiryService.editEnquiry(customerID, enquiryDetails);

		if (editedData != null) {
			return new ResponseEntity<Enquiry>(editedData, HttpStatus.OK);
		}

		throw new EnquiryNotFoundException("Enquiry not found");
	}

	@PatchMapping(value = "/update-enquiry/{customerID}")
	public ResponseEntity<Enquiry> updateEnquiry(@PathVariable int customerID, @RequestBody Enquiry enquiryDetails)
			throws EnquiryNotFoundException {

		Enquiry updateEnquiry = enquiryService.updateEnquiry(customerID, enquiryDetails);

		if (updateEnquiry != null) {
			return new ResponseEntity<Enquiry>(updateEnquiry, HttpStatus.OK);
		}
		throw new EnquiryNotFoundException("Enquiry not found");
	}

	@DeleteMapping("/delete-enquiry/{customerID}")
	public ResponseEntity<Void> deleteEnquiry(@PathVariable Integer customerID) throws EnquiryNotFoundException {
		Boolean flag = enquiryService.deleteEnquiry(customerID);
		if (flag) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		throw new EnquiryNotFoundException("Enquiry not found");

	}

	@GetMapping(value = "/expose-enquiries")
	public ResponseEntity<List<Enquiry>> getAllEnquiry() {
		List<Enquiry> enquiryList = enquiryService.getAllEnquiry();
		if (enquiryList.isEmpty()) {
			return new ResponseEntity<List<Enquiry>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Enquiry>>(enquiryList, HttpStatus.OK);

	}

	@GetMapping(value = "/expose-enquiry/{customerID}")
	public ResponseEntity<EnquiryResponseDTO> getEnquiry(@PathVariable Integer customerID) throws EnquiryNotFoundException {
		EnquiryResponseDTO getEnquiry = enquiryService.getEnquiryDetails(customerID);
		if (getEnquiry != null) {
			return new ResponseEntity<EnquiryResponseDTO>(getEnquiry, HttpStatus.OK);

		}
		throw new EnquiryNotFoundException("Enquiry Not Found for Customer ID : " + customerID);
    }
	
	@GetMapping(value = "expose-enquiries-by-status/{status}")
	public ResponseEntity<List<Enquiry>> exposeEnquiryByStatus(@PathVariable ("status") String status){
		List<Enquiry> enquiries= enquiryService.findEnquiriesByStatus(status);
		if(enquiries.size()>0) {
			return ResponseEntity.ok().body(enquiries);
		}else
			throw new EnquiryNotFoundException("Enquiry not found on status "+status);
	}
	
	@PostMapping(value = "/save-enquiry")
	public ResponseEntity<EnquiryResponseDTO> saveEnquiry(@RequestBody EnquiryRequestDTO enquiryRequestDTO) {
		EnquiryResponseDTO saveEnquiryResponseDTO = enquiryResource.saveEnquiry(enquiryRequestDTO);
       
		return new ResponseEntity<EnquiryResponseDTO>(saveEnquiryResponseDTO, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/update-enquiry-status/{customerID}/{status}")
	public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable Integer customerID,@PathVariable String status){
		Enquiry updateEnquiry = enquiryService.updateEnquiry(customerID,status);
		
		return new ResponseEntity<Enquiry>(updateEnquiry,HttpStatus.OK);
	}
}