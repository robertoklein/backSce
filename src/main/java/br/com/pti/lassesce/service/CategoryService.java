package br.com.pti.lassesce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pti.lassesce.model.Category;
import br.com.pti.lassesce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Category findOne(long id) {
		Category category = categoryRepository.findOne(id);
		return category;
	}
	/**
	 * Método responsável por inserir ou atualizar um registro, se usar POST é inserção, se usar PUT é atualização
	 * @param category
	 */
	public void insertUpdate(Category category) {
		categoryRepository.save(category);
	}
	
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
	
	public List<Category> find(){
		return categoryRepository.findAll();
	}
	
	
}
