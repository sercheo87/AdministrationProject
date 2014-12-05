package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Resources;
import com.project.exceptions.ProjectException;

@Stateless
public class ResourcesService {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Resources> getAllResources() throws ProjectException {
		try {
			Query qr = em.createNamedQuery("Resources.getAllResources");
			return qr.getResultList();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("ERROR AL INSERTAR PRODUCTO");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Resources> getInformationBasic() {
		Query qr = em.createNamedQuery("Resources.getResourcesWithType");
		return qr.getResultList();
	}
}
