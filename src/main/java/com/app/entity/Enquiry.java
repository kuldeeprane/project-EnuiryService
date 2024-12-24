package com.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@DynamicUpdate
@Entity
@Data
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerID;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Long mobileNo;
	private String pancardNo;
	@CreationTimestamp
	private Date enquiryDateTime;
	@Enumerated(EnumType.STRING)
	private EnquiryStatus status;
	@OneToOne(cascade = CascadeType.MERGE.DETACH.REMOVE.REFRESH)
	private Cibil cibil;

}
