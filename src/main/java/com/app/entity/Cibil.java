package com.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
@Entity
@Data
public class Cibil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cibilId;
	private Integer cibilScore;
	@CreationTimestamp
	private Date cibilScoreDateTime;
	private String cibilRemark;

}
