package com.ista.isp.assessment.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.service.ItemService;

/**
 * @author Fernando Moreno Ruiz
 * */
@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	public ItemController(ItemService itemService) {
		super();
		this.itemService = itemService;
	}
	
	@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody final Item item){
		return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Item> updateItem(@RequestBody Item item,@PathVariable long id){
		return new ResponseEntity<Item>(itemService.updateItem(item,id),HttpStatus.OK);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Item> getItem(@PathVariable(value = "id") long id){
		return new ResponseEntity<Item>(itemService.getItem(id),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Item>> listItems(){
		return new ResponseEntity<List<Item>>(itemService.getListItems(),HttpStatus.OK);
	}
	
}
