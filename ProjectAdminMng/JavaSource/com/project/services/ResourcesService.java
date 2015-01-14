package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Activity;
import com.project.entity.Resource;
import com.project.entity.TypeResource;
import com.project.exceptions.ProjectException;

@Stateless
public class ResourcesService {
	@PersistenceContext
	private EntityManager em;

	public void add(Resource resource) throws ProjectException {
		try {
			System.out.println("Recurso a ingresar");
			System.out.println(resource);

			Activity activity = this.em.find(Activity.class, resource.getActivity().getId());
			TypeResource typeResource = this.em.find(TypeResource.class, resource.getTypeResource().getId());

			System.out.println("Recurso filtrado");
			System.out.println(resource);

			resource.setTypeResource(typeResource);
			resource.setActivity(activity);

			this.em.persist(resource);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el recurso");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAll() throws ProjectException {
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
