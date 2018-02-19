package br.com.pti.lassesce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.lassesce.model.Category;
import br.com.pti.lassesce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	/**
	 * Método responsável por inserir uma nova categoria
	 * @param category
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createCategory(@RequestBody Category category) throws Exception {
		try {
			categoryService.insertUpdate(category);
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}

	/**
	 * Método responsável por deletar uma categoria
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
		Category category = categoryService.findOne(id);
		if(category != null) {
			categoryService.delete(category);	
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * método responsável por listar todas as categorias
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Category>> listAllCategory() {
		List<Category> list = new ArrayList<>();
			list = categoryService.find();
			if(list.isEmpty()) {
				return new ResponseEntity<>(list,null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(list,null,HttpStatus.OK);
	}

	/**
	 * Método responsável por buscar uma categoria
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Category> listOne(@PathVariable long id){
		Category category = new Category();
		category = categoryService.findOne(id);
		if(category == null) {
			return new ResponseEntity<>(category,null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(category,null,HttpStatus.OK);
	}

	/**
	 * método responsável por alterar uma categoria já cadastrada
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public void updateCategory(@RequestBody Category category) {
		categoryService.insertUpdate(category);
	}
}
