package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.StateActivity;
import com.project.exceptions.ProjectException;

@Stateless
public class StateActivityService {
	@PersistenceContext
	private EntityManager em;

	public void add(StateActivity stateActivity) throws ProjectException {
		try {
			this.em.persist(stateActivity);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el estado de la actividad");
		}
	}

	@SuppressWarnings("unchecked")
	public List<StateActivity> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("StateActivity.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los estados de la actividad");
		}
	}

	public void remove(StateActivity stateActivity) throws ProjectException {
		try {
			StateActivity item = this.em.find(StateActivity.class, stateActivity.getId());

			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el estado de la actividad");
		}
	}

	public void update(Integer id, StateActivity stateActivity) throws ProjectException {
		try {
			StateActivity item = this.em.find(StateActivity.class, id);
			item.setState(stateActivity.getState());

			this.em.merge(stateActivity);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar el estado de la actividad");
		}
	}
}
