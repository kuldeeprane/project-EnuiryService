package com.app.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Cibil;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;
import com.app.repository.EnquiryRepository;
import com.app.service.CommunicationService;
import com.app.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private ModelMapper modelMapper;

	private EnquiryRepository enquiryRepository;

	@Autowired
	private CommunicationService communicationService;

	public EnquiryServiceImpl(EnquiryRepository enquiryRepository) {
		super();
		this.enquiryRepository = enquiryRepository;
	}

	@Override

	public Boolean deleteEnquiry(Integer customerID) {
		if (enquiryRepository.existsById(customerID)) {
			enquiryRepository.deleteById(customerID);
			return true;
		}
		return false;
	}

	public Enquiry saveEnquiry(Enquiry enquiry) {

		Enquiry saveEnquiry = enquiryRepository.save(enquiry);

		if (saveEnquiry != null && saveEnquiry.getCustomerID() != 0) {
			communicationService.sendWelcomeMail(saveEnquiry);
		}
		return saveEnquiry;
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		return enquiryRepository.findAll();
	}

	@Override
	public Enquiry addData(Enquiry enquiry) {

		// enquiry.setStatus(EnquiryStatus.Register);

		return enquiryRepository.save(enquiry);
	}

	@Override
	public EnquiryResponseDTO getEnquiryDetails(Integer customerID) {
		Optional<Enquiry> optional = enquiryRepository.findById(customerID);
		if (optional.isPresent()) {
			Enquiry enquiry = optional.get();
			Cibil cibil = enquiry.getCibil();
			EnquiryResponseDTO enquiryResponseDTO = modelMapper.map(enquiry, EnquiryResponseDTO.class);

			enquiryResponseDTO.setCibilRemark(cibil.getCibilRemark());
			enquiryResponseDTO.setCibilScore(cibil.getCibilScore());

			return enquiryResponseDTO;
		}
		return null;
	}

	@Override
	public Enquiry getEnquiry(Integer customerID) {
		Optional<Enquiry> optional = enquiryRepository.findById(customerID);
		if (optional.isPresent()) {
			Enquiry enquiry = optional.get();
			Cibil cibil = enquiry.getCibil();
			EnquiryResponseDTO enquiryResponseDTO = modelMapper.map(enquiry, EnquiryResponseDTO.class);

			Enquiry enq = modelMapper.map(cibil, Enquiry.class);

			return enq;
		}
		return null;
	}

	@Override
	public Enquiry editEnquiry(int customerID, Enquiry enquiryDetails) {

		if (enquiryRepository.existsById(customerID)) {
			enquiryDetails.setCustomerID(customerID);

			return enquiryRepository.save(enquiryDetails);
		}
		return null;
	}

	@Override
	public Enquiry updateEnquiry(int customerID, Enquiry enquiryDetails) {

		if (enquiryRepository.existsById(customerID)) {

			Enquiry existingsavedData = enquiryRepository.findById(customerID).get();

			if (enquiryDetails.getFirstName() != null) {
				existingsavedData.setFirstName(enquiryDetails.getFirstName());
			}
			if (enquiryDetails.getLastName() != null) {
				existingsavedData.setLastName(enquiryDetails.getLastName());
			}
			if (enquiryDetails.getAge() != null) {
				existingsavedData.setAge(enquiryDetails.getAge());
			}
			if (enquiryDetails.getMobileNo() != null) {
				existingsavedData.setMobileNo(enquiryDetails.getMobileNo());
			}
			if (enquiryDetails.getPancardNo() != null) {
				existingsavedData.setPancardNo(enquiryDetails.getPancardNo());
			}
			if (enquiryDetails.getEmail() != null) {
				existingsavedData.setEmail(enquiryDetails.getEmail());
			}
			if (enquiryDetails.getStatus() != null) {
				existingsavedData.setStatus(enquiryDetails.getStatus());
			}

			return enquiryRepository.save(existingsavedData);
		}

		return null;
	}

	@Override
	public List<Enquiry> findEnquiriesByStatus(String status) {

		return enquiryRepository.findAllByStatus(EnquiryStatus.valueOf(status));
	}

	@Override
	public Enquiry updateEnquiry(int customerID, String status) {

		if (enquiryRepository.existsById(customerID)) {
			Enquiry enquiry = enquiryRepository.findById(customerID).get();
			enquiry.setStatus(EnquiryStatus.valueOf(status));
			Enquiry updatedEnquiry = enquiryRepository.save(enquiry);
			return updatedEnquiry;
		}

		return null;
	}

}
