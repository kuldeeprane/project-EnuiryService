package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	public List<Enquiry> findAllByStatus(EnquiryStatus status);
	

}
