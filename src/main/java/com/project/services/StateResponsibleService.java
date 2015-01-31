package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.StateResponsible;
import com.project.exceptions.ProjectException;

@Stateless
public class StateResponsibleService {
	@PersistenceContext
	private EntityManager em;

	public void add(StateResponsible stateResponsible) throws ProjectException {
		try {
			this.em.persist(stateResponsible);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el estado del responsable");
		}
	}

	@SuppressWarnings("unchecked")
	public List<StateResponsible> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("StateResponsible.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los estados de los responsables");
		}
	}

	public void remove(StateResponsible stateResponsible) throws ProjectException {
		try {
			StateResponsible item = this.em.find(StateResponsible.class, stateResponsible.getId());
			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el estado del responsable");
		}
	}

	public void update(Integer id, StateResponsible stateResponsible) throws ProjectException {
		try {
			StateResponsible item = this.em.find(StateResponsible.class, id);
			item.setState(stateResponsible.getState());

			this.em.merge(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar el estado del responsable");
		}
	}
}
