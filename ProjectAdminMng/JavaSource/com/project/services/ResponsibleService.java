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
import com.project.entity.Responsible;
import com.project.entity.StateResponsible;
import com.project.exceptions.ProjectException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ResponsibleService {

	@Resource
	private EJBContext context;
	@PersistenceContext
	private EntityManager em;

	public void add(Responsible responsible, Activity activity) throws ProjectException {

		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();

			Query query = this.em.createNamedQuery("Activity.getActivity");
			query.setParameter("id", activity.getId());

			Activity wActivity = this.em.find(Activity.class, activity.getId());
			if (wActivity == null) {
				utx.rollback();
				throw new ProjectException("Actividad no encontrada");
			}

			StateResponsible state = this.em.find(StateResponsible.class, responsible.getState().getId());
			if (state == null) {
				utx.rollback();
				throw new ProjectException("Estado del responsable no encontrado");
			}

			responsible.setState(state);
			responsible.setActivity(wActivity);

			System.out.println("ITEM ACTIVIDAD:" + wActivity);
			System.out.println("ITEM STATE:" + state);
			System.out.println("ITEM RESPONSIBLE:" + responsible);

			wActivity.getResponsibles().add(responsible);
			this.em.merge(wActivity);

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

	@SuppressWarnings("unchecked")
	public List<Responsible> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Resource.getAllResponsible");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los responsables");
		}
	}

	public void remove(Activity activity, Responsible responsible) throws ProjectException {
		try {
			UserTransaction utx = this.context.getUserTransaction();
			utx.begin();
			Activity act = this.em.find(Activity.class, activity.getId());

			Responsible item = this.em.find(Responsible.class, responsible.getId());
			if (item != null) {
				act.getResponsibles().remove(item);
				utx.commit();
			} else {
				utx.rollback();
				throw new ProjectException("Responsable no encontrado");
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}

	}
}
