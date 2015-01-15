package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Activity;
import com.project.exceptions.ProjectException;

@Stateless
public class ActivityService {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Activity> getAll() throws ProjectException {
		Query query = this.em.createQuery("SELECT e FROM Activity e JOIN FETCH e.resources");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(query.getResultList());
		return query.getResultList();
	}
}
