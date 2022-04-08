package com.ista.isp.assessment.todo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ista.isp.assessment.todo.controller.ItemController;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.repository.ItemRepository;
import com.ista.isp.assessment.todo.service.impl.ItemServiceImpl;

/**
 * @author Fernando Moreno Ruiz
 * */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TodoApplicationTests {

	@Autowired
	ItemRepository itemRepository;
	
	
	private Item item1 = new Item(1,"Test getItems","Debo crear el test para el getItems",true);
	private Item item2 = new Item(2,"Test createContext","Debo crear el test con createItem.",true);
	private Item item3 = new Item(3,"Test getItem","Debo crear el test para el getItem",true);
	private Item item4 = new Item(4,"Test updateItem","Debo crear el test para el updateItem",false);
	
	private ItemController createItemController() {
		return new ItemController(new ItemServiceImpl(itemRepository));
	}
	
	@Test
	@Order(1)
	void contextLoads() {
		ItemController itemController = createItemController();
		itemController.createItem(item1);		
		itemController.createItem(item2);		
		itemController.createItem(item3);
		itemController.createItem(item4);
	}
	
	@Test
	@Order(2)
	void getListItemsTest() {
		ItemController itemController = createItemController();		
		ResponseEntity<List<Item>> response = itemController.listItems();
		assertArrayEquals(new Item[] {item1,item2,item3,item4} , response.getBody().toArray());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@Order(3)
	void getItemTest() {
		ItemController itemController = createItemController();
		ResponseEntity<Item> response = itemController.getItem(1);		
		assertEquals(response.getBody(), item1);		
	}
	
	@Test
	@Order(4)
	void updateItemTest() {
		ItemController itemController = createItemController();
		item1.setTitle("Updated Item");
		itemController.updateItem(item1,1);
		Item updatedItem = itemController.getItem(1).getBody();
		assertEquals(item1, updatedItem);
	}

}
