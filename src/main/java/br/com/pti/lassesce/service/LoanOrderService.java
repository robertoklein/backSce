package br.com.pti.lassesce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pti.lassesce.model.LoanOrder;
import br.com.pti.lassesce.repository.LoanOrderRepository;

@Service
public class LoanOrderService {

	@Autowired
	LoanOrderRepository loanOrderRepository;

	public void insert(LoanOrder loanOrder) {
		loanOrderRepository.save(loanOrder);
	}

	public LoanOrder findOne(long id) {
		LoanOrder loan = loanOrderRepository.findOne(id);
		return loan;
	}

	public void delete(long id) {
		loanOrderRepository.delete(id);
	}

	public List<LoanOrder> find(){
		return loanOrderRepository.findAll();
	}	
	
	// ==================== Serviços para adm ==================== 
	/**
	 * Método responsável por retornar as loan atrasadas
	 */
	public List<LoanOrder> getLateLoan(){
		LocalDate dayDate = LocalDate.now();
		List<LoanOrder> lateLoanList = loanOrderRepository.findByPulloutLessThanAndRefoundLessThanAndReturnedFalse(dayDate,dayDate);
		return lateLoanList;
	}
	/**
	 * Método responsável por retornar todas as loans que devem ser entregues no dia corrente.
	 */
	public List<LoanOrder> getDayRefound() {
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByRefoundEqualsAndDeliveredFalse(today);
		return loanList;
	}
	
	/**
	 * Método responsável por retornar todas as loan em aberto
	 */
	public List<LoanOrder> getOpenLoan(){
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByPulloutLessThanEqualAndRefoundGreaterThanAndDeliveredTrue(today,today);
		return loanList;
	}
	
	/**
	 * Método responsável por retornar todas os agendamentos de loans
	 */
	public List<LoanOrder> getScheculed(){
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByPulloutGreaterThanEqualAndDeliveredFalse(today);
		return loanList;
	}
	/**
	 * Método responsável por retornar as entregas programadas para o dia corrente.
	 */
	public List<LoanOrder> getDayPullout() {
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByPulloutEqualsAndDeliveredFalse(today);
		return loanList;
	}
	
	// ==================== Serviços para user ====================
	
	/**
	 * Método responsável por retornar as loan atrasadas ByUser
	 */
	public List<LoanOrder> getLateLoanByUser(long id){
		LocalDate dayDate = LocalDate.now();
		List<LoanOrder> lateLoanList = loanOrderRepository.findByUserIdAndPulloutLessThanAndRefoundLessThanAndReturnedFalse(id,dayDate,dayDate);
		return lateLoanList;
	}
	/**
	 * Método responsável por retornar todas as loans que devem ser entregues no dia corrente ByUser
	 */
	public List<LoanOrder> getDayRefoundByUser(long id) {
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByUserIdAndRefoundEqualsAndDeliveredFalse(id,today);
		return loanList;
	}
	
	/**
	 * Método responsável por retornar todas as loan em aberto ByUser
	 */
	public List<LoanOrder> getOpenLoanByUser(long id){
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByUserIdAndPulloutLessThanEqualAndRefoundGreaterThanAndDeliveredTrue(id,today,today);
		return loanList;
	}
	
	/**
	 * Método responsável por retornar todas os agendamentos de loans ByUser
	 */
	public List<LoanOrder> getScheculedByUser(long id){
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByUserIdAndPulloutGreaterThanEqualAndDeliveredFalse(id,today);
		return loanList;
	}
	/**
	 * Método responsável por retornar as entregas programadas para o dia corrente ByUser
	 */
	public List<LoanOrder> getDayPulloutByUser(long id) {
		LocalDate today = LocalDate.now();
		List<LoanOrder> loanList = loanOrderRepository.findByUserIdAndPulloutEqualsAndDeliveredFalse(id,today);
		return loanList;
	}
}
