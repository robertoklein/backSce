package br.com.pti.lassesce.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pti.lassesce.model.Equipment;
import br.com.pti.lassesce.model.LoanOrder;
import br.com.pti.lassesce.repository.EquipmentRepository;
import br.com.pti.lassesce.repository.LoanOrderRepository;

@Service
public class EquipmentService {

	@Autowired
	EquipmentRepository equipmentRepository;

	@Autowired
	LoanOrderRepository loanOrderRepository;

	public Equipment findOne(long id) {
		Equipment equipment = equipmentRepository.findOne(id);
		return equipment;
	}

	/**
	 * Método responsável por inserir ou atualizar um registro, se usar POST é inserção, se usar PUT é atualização
	 * @param equipment
	 */
	public void insertUpdate(Equipment equipment) {
		equipmentRepository.save(equipment);
	}

	/**
	 * Método responsável por deletar um equipamento
	 * @param equipment
	 */
	public void delete(Equipment equipment) {
		equipmentRepository.delete(equipment);
	}

	
	public List<Equipment> find(){
		return equipmentRepository.findAll();
	}
	
	/**
	 * Método responsável por realizar a busca de equipamentos a partir da string do nome, funcionalidade
	 * da barre de pesquisa de equipamentos.
	 * @param equipName
	 * @param data1
	 * @param data2
	 * @return
	 */
	public List<Equipment> equipmentResult(String equipName,LocalDate data1,LocalDate data2){
		//busca todos os equipamentos contendo a string passada
		List<Equipment> equipList = equipmentRepository.findByNameIgnoreCaseContaining(equipName);
		//busca todas as loan no intervalo de tempo
		List<LoanOrder> loanList = loanOrderRepository.findByDateBetween(data1, data2);
		//lista de equipamentos utilizados nas loans no intervalo de tempo
		List<Equipment> usedEquipmentList = new ArrayList<>();
		//pega todos os equipamentos de todas as ordens de emprestimo no intervalo de tempo
		for(LoanOrder l : loanList) {
			System.out.println("loan id: " + l.getId());
			List<Equipment> equipmentLoanList = l.getEquipments();
			for(Equipment e : equipmentLoanList) {
				System.out.println("adicionado na usedEquip: " + e.getName());
				usedEquipmentList.add(e);
			}
		}
		for(Equipment e : usedEquipmentList) {
			for(Equipment eq : equipList) {
				if(e.getId() == eq.getId()) {
					e.setUnavailable(true);
				}
			}
		}
		return equipList;
	}

	public Page<Equipment> getAllEquipmentsPage(Pageable pageable, LocalDate date1, LocalDate date2) {

		Page<Equipment> equipmentListPage = equipmentRepository.findAll(pageable);
		//busca todas as loan no intervalo de tempo
		List<LoanOrder> loanList = loanOrderRepository.findByDateBetween(date1, date2);
		//lista de equipamentos utilizados nas ordens de emprestimo
		List<Equipment> usedEquipmentList = new ArrayList<>();
		//para cada item loan da lista, busca os equipamentos de cada loan
		for(LoanOrder obj : loanList) {
			//pega os equipamentos da loan sendo iterada
			List<Equipment> list = obj.getEquipments();
			//adiciona cada equipamento da loan, na lista dos indisponíveis
			for(Equipment e : list ) {
				usedEquipmentList.add(e);
			}
		}
		for(Equipment e : usedEquipmentList) {
			for(Equipment eq : equipmentListPage) {
				if(e.getId() == eq.getId()) {	
					eq.setUnavailable(true);
				}
			}
		}
		return equipmentListPage;
	}

	public List<LocalDate> checkAvailability(LocalDate date, long EquipmentId) { 

		date = LocalDate.of(date.getYear(),
				date.getMonth().getValue(),
				date.getDayOfMonth());
		LocalDate start = date.withDayOfMonth(1);
		LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
		//retorna uma lista de todas as loanOrder no periodo (mes) começando em start e até end
		List<LoanOrder> loanList = loanOrderRepository.findByDateBetween(start, end);
		List<LocalDate> monthAvailability = new ArrayList<>();
		//itera todas as ordens de emprestimo no periodo
		for(LoanOrder l : loanList) {
			List<Equipment> equipList = l.getEquipments();
			//itera a lista de equipamento de cada ordem de emprestimo
			for(Equipment e : equipList) {
				//entra se o equipamento recebido esta contido na lista de equipamento da ordem em questao
				if(e.getId() == EquipmentId) {
					LocalDate fallBack = l.getPullout();
					LocalDate refound = l.getRefound();
					//pega a diferença de dias entre a retirada e devolução				
					long daysBetween = ChronoUnit.DAYS.between(fallBack, refound);	
					//adiciona o primeiro dia a lista (data de retirada)
					monthAvailability.add(fallBack);
					int i = 0;
					while(i < daysBetween) {
						fallBack = fallBack.plusDays(1);
						monthAvailability.add(fallBack);
						i++;
					}
				}
			}
		}
		return monthAvailability;
	}
}
