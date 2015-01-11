package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.TypeResource;
import com.project.exceptions.ProjectException;

@Stateless
public class TypesResourcesService {
	@PersistenceContext
	private EntityManager em;

	public void add(TypeResource typeResource) throws ProjectException {
		try {
			this.em.persist(typeResource);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el tipo de recurso");
		}
	}

	@SuppressWarnings("unchecked")
	public List<TypeResource> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("TypeResource.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los tipos de recursos");
		}
	}

	public void remove(TypeResource typeResource) throws ProjectException {
		try {
			TypeResource item = this.em.find(TypeResource.class, typeResource.getId());

			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el tipo de recurso");
		}
	}

	public void update(Integer id, TypeResource typeResource) throws ProjectException {
		try {
			TypeResource item = this.em.find(TypeResource.class, id);
			item.setDescription(typeResource.getDescription());
			item.setName(typeResource.getName());

			this.em.merge(typeResource);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar el tipo de recurso");
		}
	}
}
