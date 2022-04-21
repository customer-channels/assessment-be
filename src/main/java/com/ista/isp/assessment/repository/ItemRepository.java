package com.ista.isp.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ista.isp.assessment.model.Item;

/**
 * @author Fernando Moreno Ruiz
 * */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
