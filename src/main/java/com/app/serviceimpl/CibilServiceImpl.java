package com.app.serviceimpl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.app.dto.CibilRequestDTO;
import com.app.entity.Cibil;
import com.app.repository.CibilRepository;
import com.app.service.CibilService;

@Service
public class CibilServiceImpl implements CibilService {

	private CibilRepository cibilRepository;

	public CibilServiceImpl(CibilRepository cibilRepository) {
		super();
		this.cibilRepository = cibilRepository;
	}

	@Override
	public Cibil saveEnquiry(Cibil cibil) {

		return cibilRepository.save(cibil);
	}

	@Override
	public Cibil generateRandomCibilScore(Cibil cibil) {
		Random random = new Random();

		int x = random.nextInt(601) + 300;
		cibil.setCibilScore(x);

		if (x >= 300 && x <= 549) {
			cibil.setCibilRemark("Poor");
		} else if (x >= 550 && x <= 649) {
			cibil.setCibilRemark("Average");
		} else if (x >= 650 && x <= 749) {
			cibil.setCibilRemark("Good");
		} else {
			cibil.setCibilRemark("Excellent");
		}

		return cibilRepository.save(cibil);
	}

}
