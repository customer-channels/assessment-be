package com.ista.isp.assessment;

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

import com.ista.isp.assessment.controller.ItemController;
import com.ista.isp.assessment.model.Item;
import com.ista.isp.assessment.repository.ItemRepository;
import com.ista.isp.assessment.service.ItemService;
import com.ista.isp.assessment.service.impl.ItemServiceImpl;

/**
 * @author Fernando Moreno Ruiz
 * */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	ItemService itemService;
	
	
	private Item item1 = new Item(1,"Test getItems","Debo crear el test para el getItems",true);
	private Item item2 = new Item(2,"Test createContext","Debo crear el test con createItem.",true);
	private Item item3 = new Item(3,"Test getItem","Debo crear el test para el getItem",true);
	private Item item4 = new Item(4,"Test updateItem","Debo crear el test para el updateItem",false);
	
	private ItemController createItemController() {
		return new ItemController(itemService);
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
		
		// I compare if all items are in the response
		assertArrayEquals(new Item[] {item1,item2,item3,item4} , response.getBody().toArray());
		
		// I check also status response
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
		
		// I modify an item
		item1.setTitle("Updated Item");
		
		// I change item 1 on database
		itemController.updateItem(item1,1);
		
		// I get the item 1 from database
		Item updatedItem = itemController.getItem(1).getBody();
		
		// I compare if the item from database is the same as the modified item
		assertEquals(item1, updatedItem);
	}

}
