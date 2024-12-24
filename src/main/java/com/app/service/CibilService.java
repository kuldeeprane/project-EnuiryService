package com.app.service;

import com.app.entity.Cibil;

public interface CibilService {

	Cibil saveEnquiry(Cibil cibil);

	Cibil generateRandomCibilScore(Cibil cibil);

}
