package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Activity;
import com.project.entity.StateActivity;
import com.project.exceptions.ProjectException;

@Stateless
public class ActivityService {
	@PersistenceContext
	private EntityManager em;

	public void add(Activity activity) throws ProjectException {
		try {
			System.out.println("Recurso a ingresar");
			System.out.println(activity);

			StateActivity stateActivity = this.em.find(StateActivity.class, activity.getState().getId());

			activity.setState(stateActivity);

			this.em.persist(activity);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el recurso");
		}
	}

	public Activity getActivity(Activity activity) throws ProjectException {
		Activity wActivity = this.em.find(Activity.class, activity.getId());
		if (wActivity != null) {
			return wActivity;
		} else {
			throw new ProjectException("No se encuentra la actividad");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Activity.getAll");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	public void remove(Activity activity) throws ProjectException {
		try {
			Activity act = this.em.find(Activity.class, activity.getId());
			if (act == null) {
				throw new ProjectException("No se encuentra la actividad");
			}
			this.em.remove(act);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}
}
