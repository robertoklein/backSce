package br.com.pti.lassesce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pti.lassesce.model.Equipment;

@Repository
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long>{
	
	List<Equipment> findByNameIgnoreCaseContainingAndActiveTrue(@Param("string") String string);

	Page<Equipment> findAll(Pageable pageable);
	
	List<Equipment> findByActiveTrue();	
	
	Equipment findBySerial(String serial);
}
