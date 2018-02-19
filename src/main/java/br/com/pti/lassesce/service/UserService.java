package br.com.pti.lassesce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pti.lassesce.model.User;
import br.com.pti.lassesce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Método responsável por inserir ou atualizar um registro, se usar POST é inserção, se usar PUT é atualização
	 * @param user
	 */
	public void insertUpdate(User user) {
		userRepository.save(user);
	}
	
	public User findOne(long id) {
		User user = userRepository.findOne(id);
		return user;
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public List<User> find(){
		return userRepository.findAll();
	}
	
	public List<User> findAdmin(){
		return  userRepository.findByIsAdminTrue();
	}
}
