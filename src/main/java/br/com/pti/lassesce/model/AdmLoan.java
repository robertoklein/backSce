package br.com.pti.lassesce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe responsável por somente controlar qual adm realizou a entrega e a devolução de uma ordem de empréstimo.
 * @author roberto.klein
 *
 */
@Entity
@Table(name="admLoan")
public class AdmLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	private LoanOrder loan;
	
	@ManyToOne(optional = false)
	private User admDelivered;
	
	@ManyToOne(optional = false)
	private User admReturned;

	public LoanOrder getLoan() {
		return loan;
	}

	public void setLoan(LoanOrder loan) {
		this.loan = loan;
	}

	public User getAdmDelivered() {
		return admDelivered;
	}

	public void setAdmDelivered(User admDelivered) {
		this.admDelivered = admDelivered;
	}

	public User getAdmReturned() {
		return admReturned;
	}

	public void setAdmReturned(User admReturned) {
		this.admReturned = admReturned;
	}
}
