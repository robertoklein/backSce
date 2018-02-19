package br.com.pti.lassesce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.lassesce.model.Dashboard;
import br.com.pti.lassesce.service.LoanOrderService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	LoanOrderService loanOrderService;
	
	/**
	 * Método responsável por retornar os count do dashboard do admin.
	 * @return
	 */
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public ResponseEntity<Dashboard> intDashboard() {
		Dashboard dashboard = new Dashboard();
		//seta a quantidade das loans atrasadas
		dashboard.setLateLoans(loanOrderService.getLateLoan().size());		
		//seta a quantidade de loans a ser devolvida no dia corrente
		dashboard.setDayRefound(loanOrderService.getDayRefound().size());
		//seta a quantidade de loans em aberto
		dashboard.setOpenLoans(loanOrderService.getOpenLoan().size());
		dashboard.setTotalSchedules(loanOrderService.getScheculed().size());
		//retirada pra hoje
		dashboard.setDayPullout(loanOrderService.getDayPullout().size());
		return new ResponseEntity<>(dashboard,null,HttpStatus.OK);
	}	
	
	/**
	 * Método responsável por retornar o count do dashboard por usuario
	 * @return
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<Dashboard> intDashboardUser(@PathVariable long id) {
		System.out.println("entrou no dashboard!!!!!!!");
		Dashboard dashboard = new Dashboard();
		//seta a quantidade das loans atrasadas
		dashboard.setLateLoans(loanOrderService.getLateLoanByUser(id).size());		
		//seta a quantidade de loans a ser devolvida no dia corrente
		dashboard.setDayRefound(loanOrderService.getDayRefoundByUser(id).size());
		//seta a quantidade de loans em aberto
		dashboard.setOpenLoans(loanOrderService.getOpenLoanByUser(id).size());
		//seta a quantidade de loans agendadas
		dashboard.setTotalSchedules(loanOrderService.getScheculedByUser(id).size());
		//seta a quantidade de loans para retirada no dia corrente
		dashboard.setDayPullout(loanOrderService.getDayPulloutByUser(id).size());
		return new ResponseEntity<>(dashboard,null,HttpStatus.OK);
	}	
}
