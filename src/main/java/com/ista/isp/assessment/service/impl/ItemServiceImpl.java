package com.ista.isp.assessment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.isp.assessment.exception.ResourceNotFoundException;
import com.ista.isp.assessment.model.Item;
import com.ista.isp.assessment.repository.ItemRepository;
import com.ista.isp.assessment.service.ItemService;

/**
 * @author Fernando Moreno Ruiz
 * */
@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	
	public ItemServiceImpl(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	public Item saveItem(Item item) {				
		return itemRepository.save(item);
	}

	@Override
	public Item getItem(long id) throws ResourceNotFoundException{ 
		return getItemWithExceptionNotFound(id);
	}
	
	@Override
	public List<Item> getListItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item updateItem(Item item, long id) throws ResourceNotFoundException {
		Item existingItem = getItemWithExceptionNotFound(id);
		
		existingItem.setChecked(item.isChecked());
		existingItem.setTitle(item.getTitle());
		existingItem.setDescription(item.getDescription());
		
		itemRepository.save(existingItem);
		
		return existingItem;
	}
	
	private Item getItemWithExceptionNotFound(long id) throws ResourceNotFoundException {
		return itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item", "id", id));
	}

}
