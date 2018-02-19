package br.com.pti.lassesce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pti.lassesce.model.AdmLoan;
import br.com.pti.lassesce.repository.AdmLoanRepository;

@Service
public class AdmLoanService {

	@Autowired
	AdmLoanRepository admLoanRepository;
	
	public void saveAdmLoan(AdmLoan admLoan) {
		admLoanRepository.save(admLoan);
	}
	
}
