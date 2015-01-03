package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.User;

@Stateless
public class UserServicio {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<User> recuperartodos() {
		Query qr = this.em.createQuery("SELECT cl FROM User cl");
		return qr.getResultList();
	}
}
