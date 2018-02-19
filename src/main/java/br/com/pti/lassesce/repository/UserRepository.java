package br.com.pti.lassesce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.lassesce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	/**
	 * Retorna todos os admin
	 * @param daydate
	 * @return
	 */
	List<User> findByIsAdminTrue();
}
