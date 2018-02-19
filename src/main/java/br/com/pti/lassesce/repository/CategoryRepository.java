package br.com.pti.lassesce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.lassesce.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

	
}
