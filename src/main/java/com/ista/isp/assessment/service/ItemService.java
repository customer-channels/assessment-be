package com.ista.isp.assessment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.isp.assessment.model.Item;

@Service
public interface ItemService {
	public Item saveItem(Item item);
	public List<Item> getListItems();
	public Item getItem(long id);
	public Item updateItem(Item item, long id);
}
