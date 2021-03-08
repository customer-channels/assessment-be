package com.ista.isp.assessment.todo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ista.isp.assessment.todo.dto.SearchCriteria;
import com.ista.isp.assessment.todo.model.Task;

public class TaskSpecification implements Specification<Task> {
	
	private static final long serialVersionUID = 6333565152290364758L;
	
	private List<SearchCriteria> criterias;
		
	public TaskSpecification() {
		this.criterias = new ArrayList<>();
	}
	
	public TaskSpecification(List<SearchCriteria> criterias) {
		this.criterias = criterias;
	}
	
	public void add(SearchCriteria criteria) {
		criterias.add(criteria);
    }

	@Override
	public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		List<Predicate> predicates = new ArrayList<>();
		
		for (SearchCriteria criteria : criterias) {
			
			if (criteria.getKey().equals("id"))
				predicates.add(builder.equal(root.get("id"), criteria.getValue()));
			
			if (criteria.getKey().equals("text"))
				predicates.add(builder.like(root.get("text"), "%" + criteria.getValue().toString() + "%"));
			
			if (criteria.getKey().equals("checked"))
				predicates.add(builder.equal(root.get("checked"), Boolean.valueOf(criteria.getValue().toString())));
		}
		
		return query.where(builder.and(predicates.toArray(new Predicate[0])))
				.orderBy(builder.desc(root.get("checked")))
				.getRestriction();
	}

}
