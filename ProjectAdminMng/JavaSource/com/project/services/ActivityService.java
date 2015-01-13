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

	public void add(Activity activity) throws ProjectException {
		try {
			this.em.persist(activity);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando la actividad");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Activity.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de la actividad");
		}
	}

	public void remove(Activity activity) throws ProjectException {
		try {
			Activity item = this.em.find(Activity.class, activity.getId());
			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar la actividad");
		}
	}

	public void update(Integer id, Activity activity) throws ProjectException {
		try {
			Activity item = this.em.find(Activity.class, id);
			item.setDateFinish(activity.getDateFinish());
			item.setDateStart(activity.getDateStart());
			item.setDescription(activity.getDescription());
			item.setDurationDays(activity.getDurationDays());
			item.setDurationHours(activity.getDurationHours());
			item.setState(activity.getState());

			this.em.merge(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar la actividad");
		}
	}
}
