package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Resource;
import com.project.exceptions.ProjectException;

@Stateless
public class ResourcesService {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Resource> getAllResources() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Resource.getAllResources");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de recursos");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getInformationBasic() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Resource.getResourcesWithType");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de recursos con tipo");
		}
	}
}
