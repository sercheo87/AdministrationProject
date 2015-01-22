package com.project.services;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.project.entity.Activity;
import com.project.entity.ResourceActivity;
import com.project.entity.StateActivity;
import com.project.entity.TypeResource;
import com.project.exceptions.ProjectException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ActivityService {

	@Resource
	private EJBContext context;
	@PersistenceContext
	private EntityManager em;

	public void add(Activity activity) throws ProjectException {

		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();

			System.out.println("Recurso a ingresar");
			System.out.println(activity);

			StateActivity stateActivity = this.em.find(StateActivity.class, activity.getState().getId());
			activity.setState(stateActivity);

			if (activity.getResources() != null) {
				for (ResourceActivity resource : activity.getResources()) {
					resource.setActivity(activity);
					TypeResource type = this.em.find(TypeResource.class, resource.getTypeResource().getId());
					resource.setTypeResource(type);
				}
			}

			this.em.persist(activity);
			try {
				utx.commit();
			} catch (RollbackException e) {
				e.printStackTrace();
				utx.rollback();
			} catch (HeuristicMixedException e) {
				e.printStackTrace();
				utx.rollback();
			} catch (HeuristicRollbackException e) {
				e.printStackTrace();
				utx.rollback();
			}

		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("Error de Infraestructura");
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

		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();
			Activity act = this.em.find(Activity.class, activity.getId());
			if (act == null) {
				throw new ProjectException("No se encuentra la actividad");
			}
			this.em.remove(act);

			try {
				utx.commit();
			} catch (RollbackException e) {
				e.printStackTrace();
				utx.rollback();
			} catch (HeuristicMixedException e) {
				e.printStackTrace();
				utx.rollback();
			} catch (HeuristicRollbackException e) {
				e.printStackTrace();
				utx.rollback();
			}

		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("Error de Infraestructura");
		}
	}
}
