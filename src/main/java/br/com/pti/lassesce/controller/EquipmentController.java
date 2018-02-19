package br.com.pti.lassesce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.lassesce.model.Equipment;
import br.com.pti.lassesce.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	EquipmentService equipmentService;

	/**
	 * método responsável por criar um equipamento
	 * @param equipment
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Equipment equipment) throws Exception {
		try {
			equipmentService.insertUpdate(equipment);
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}

	/**
	 * método responsavel por apagar um equipamento
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		Equipment equipment = equipmentService.findOne(id);
		if(equipment != null) {
			equipmentService.delete(equipment);	
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * método responsável por retornar todos os equipamentos 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Equipment>> listAll (){
		List<Equipment> list = new ArrayList<>();
		list = equipmentService.find();
		if(list.isEmpty()) {
			return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,null,HttpStatus.OK);
	}

	/**
	 * método responsável por retornar um equipamento pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Equipment> listOne(@PathVariable long id){
		Equipment equipment = new Equipment();
		equipment = equipmentService.findOne(id);
		if(equipment == null) {
			return new ResponseEntity<>(equipment,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(equipment,null,HttpStatus.OK);
	}

	/**
	 * método responsável por alterar um equipamento já cadastrada
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public void updateCategory(@RequestBody Equipment equipment) {
		equipmentService.insertUpdate(equipment);
	}

	/**
	 * Método responsável por realizar a busca de equipamentos a partir da string do nome, funcionalidade
	 * da barre de pesquisa de equipamentos.
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/find/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Equipment>> getByString(@PathVariable String name) {

		//colocar as datas no parametro, apos testar
		LocalDate data1 = LocalDate.of(2018, 1, 5);
		LocalDate data2 = LocalDate.of(2018, 1, 6);

		List<Equipment> list = equipmentService.equipmentResult(name,data1,data2);

		if(list.isEmpty()) {
			return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,null,HttpStatus.OK);
	}

	/**
	 * Método responsável por retornar todos os equipamentos paginados, funcionaldiade do botao buscar equipamentos 
	 * da barra de pesquisa
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Equipment>> getPageEquipment(Pageable pageable){
		//yyyy-mm-dd (retirar depois de testar ***************)
		LocalDate date1 = LocalDate.of(2018, 2, 10);
		LocalDate date2 = LocalDate.of(2018, 2, 18);
		Page<Equipment> result = equipmentService.getAllEquipmentsPage(pageable,date1, date2);
		if(result.getContent().isEmpty()) {
			return new ResponseEntity<>(result,null,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(result,null,HttpStatus.OK);
	}

	/**
	 * metodo responsável por devolver a disponibilidade do equipamento no mes inteiro
	 * @param id
	 * @param date
	 */
	@RequestMapping(value="/check/{EquipmentId}", method=RequestMethod.GET)
	public ResponseEntity<List<LocalDate>> checkAvailability(@PathVariable long EquipmentId, @RequestParam(name="date") @DateTimeFormat(iso = ISO.DATE) LocalDate date ) {	
		List<LocalDate> availabilityList = equipmentService.checkAvailability(date, EquipmentId);
		if(availabilityList.isEmpty()) {
			return new ResponseEntity<>(availabilityList,null,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(availabilityList,null,HttpStatus.OK);
	}	
}
