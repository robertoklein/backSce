package br.com.pti.lassesce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.lassesce.model.LoanOrder;
import br.com.pti.lassesce.service.LoanOrderService;

@RestController
@RequestMapping("/loanOrder")
public class LoanOrderController {

	@Autowired
	LoanOrderService loanOrderService;
	
	/**
	 * Cria uma nova ordem de empréstimo
	 * @param loanOrder
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createLoanOrder (@RequestBody LoanOrder loanOrder) throws Exception {
		try {
			loanOrderService.insert(loanOrder);
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}
	
	/**
	 * Deleta uma ordem de empréstimo
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteLoan(@PathVariable long id) {
		LoanOrder loan = loanOrderService.findOne(id);
		if(loan != null) {
			loanOrderService.delete(loan.getId());	
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retorna a lista com todas as ordens de empréstimo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> listAll(){
	 List<LoanOrder> list = new ArrayList<>();
	 list = loanOrderService.find();
	 if(list.isEmpty()) {
		 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,null,HttpStatus.OK);
	} 
	
	/**
	 * Método responsável por retornar todas as loans atrasadas
	 * @return
	 */
	@RequestMapping(value="/late", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getLateLoanlist() {
		 List<LoanOrder> list = loanOrderService.getLateLoan();
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsavel por retornar todas as loan a serem entregues no dia corrente
	 * @return
	 */
	@RequestMapping(value="/dayRefound", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getdayRefoundList() {
		 List<LoanOrder> list = loanOrderService.getDayRefound();
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * método responsável por retornar todas as loan em aberto
	 * @return
	 */
	@RequestMapping(value="/openLoan", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getopenLoanList() {
		 List<LoanOrder> list = loanOrderService.getOpenLoan();
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsável por retornar todas as loan agendadas
	 * @return
	 */
	@RequestMapping(value="/scheduled", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getScheduledLoanOrderList() {
		 List<LoanOrder> list = loanOrderService.getScheculed();
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsável por realizar a alteracao da flag delivered, ou seja, quando a loan for entregue ao colaborador pelo adm
	 * @param loan
	 * @throws Exception
	 */
	@RequestMapping(value="deliveredLoan", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	//@RequestParam(name="userAdmId") long id
	public void setDevilered(@RequestBody LoanOrder loan) throws Exception {
		loan.setDelivered(true);
		try {
			loanOrderService.insert(loan);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Método responsável por realizar a alteracao da flag returned, ou seja, quando a loan for devolvida do colaborador ao adm
	 * @param loan
	 * @throws Exception 
	 */
	@RequestMapping(value="returnedLoan", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void setRefound(@RequestBody LoanOrder loan) throws Exception {
		loan.setReturned(true);
		try {
		loanOrderService.insert(loan);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@RequestMapping(value="/scheduled/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getScheduledLoanByUser(@PathVariable long id) {
		List<LoanOrder> list = loanOrderService.getScheculedByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	@RequestMapping(value="/dayPullout/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getDayPulloutByUser(@PathVariable long id) {
		List<LoanOrder> list = loanOrderService.getDayPulloutByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	@RequestMapping(value="/lateLoan/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getLateLoanByUser(@PathVariable long id) {
		List<LoanOrder> list = loanOrderService.getLateLoanByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	@RequestMapping(value="/openLoan/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getOpenLoanByUser(@PathVariable long id) {
		List<LoanOrder> list = loanOrderService.getOpenLoanByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	@RequestMapping(value="/dayRefound/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getDayRefoundByUser(@PathVariable long id) {
		List<LoanOrder> list = loanOrderService.getDayRefoundByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	
}
	