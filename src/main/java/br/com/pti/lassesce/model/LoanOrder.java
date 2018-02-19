package br.com.pti.lassesce.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="loanOrder")
public class LoanOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany
	@Column(name="equipmentId")
	private List<Equipment> equipments;
	
	private LocalDate pullout;
	
	private LocalDate refound;
	
	private String motivation;
	
	@ManyToOne(optional = false)
	private User user;
	
	//campo para saber se foi entregue ao colaborador.
	private boolean delivered;
	
	//campo para saber se foi devolvido pelo colaborador.
	private boolean returned;

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public LocalDate getPullout() {
		return pullout;
	}

	public void setPullout(LocalDate pullout) {
		this.pullout = pullout;
	}

	public LocalDate getRefound() {
		return refound;
	}

	public void setRefound(LocalDate refound) {
		this.refound = refound;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
