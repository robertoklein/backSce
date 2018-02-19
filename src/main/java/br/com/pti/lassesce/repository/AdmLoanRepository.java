package br.com.pti.lassesce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.lassesce.model.AdmLoan;

@Repository
public interface AdmLoanRepository extends JpaRepository<AdmLoan, Long>{

	
	
	
}
