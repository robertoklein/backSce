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
import br.com.pti.lassesce.model.User;
import br.com.pti.lassesce.service.LoanOrderService;
import br.com.pti.lassesce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	LoanOrderService loanOrderService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser (@RequestBody User user) throws Exception {
		try {
			userService.insertUpdate(user);
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void alterUser(@RequestBody User user) {
		userService.insertUpdate(user);
	}
	
	/**
	 * método responsável por listar todas os usuarios
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> list = new ArrayList<>();
			list = userService.find();
			if(list.isEmpty()) {
				return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}

	/**
	 * Método responsável por buscar um usuario
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> listOne(@PathVariable long id){
		User user = new User();
		user = userService.findOne(id);
		if(user == null) {
			return new ResponseEntity<>(user,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user,null,HttpStatus.OK);
	}
	
	/**
	 * lista todos os admin
	 * @return
	 */
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllAdmin(){
		List<User> list = new ArrayList<>();
		list = userService.findAdmin();
		if(list.isEmpty()) {
			return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsável por retornar todas as loans atrasadas byUser
	 * @return
	 */
	@RequestMapping(value="/{id}/lateLoan", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getLateLoanlist(@PathVariable long id) {
		 List<LoanOrder> list = loanOrderService.getLateLoanByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsavel por retornar todas as loan a serem entregues no dia corrente byUser
	 * @return
	 */
	@RequestMapping(value="/{id}/dayRefound", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getdayRefoundList(@PathVariable long id) {
		 List<LoanOrder> list = loanOrderService.getDayRefoundByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * método responsável por retornar todas as loan em aberto byUser
	 * @return
	 */
	@RequestMapping(value="{id}/openLoan", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getopenLoanList(@PathVariable long id) {
		 List<LoanOrder> list = loanOrderService.getOpenLoanByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	/**
	 * Método responsável por retornar todas as loan agendadas byUser
	 * @return
	 */
	@RequestMapping(value="/{id}/scheduledLoan", method=RequestMethod.GET)
	public ResponseEntity<List<LoanOrder>> getScheduledLoanOrderList(@PathVariable long id) {
		 List<LoanOrder> list = loanOrderService.getScheculedByUser(id);
		 if(list.isEmpty()) {
			 return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}
	
	
}
