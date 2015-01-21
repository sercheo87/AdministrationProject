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
import com.project.entity.TypeResource;
import com.project.exceptions.ProjectException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ResourcesService {

	@Resource
	private EJBContext context;
	@PersistenceContext
	private EntityManager em;

	public void add(ResourceActivity resource, Activity activity) throws ProjectException {

		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();
			Activity wActivity = this.em.find(Activity.class, activity.getId());
			TypeResource typeResource = this.em.find(TypeResource.class, resource.getTypeResource().getId());
			resource.setTypeResource(typeResource);
			if (wActivity != null) {
				resource.setActivity(wActivity);
				wActivity.getResources().add(resource);
				this.em.merge(wActivity);
				utx.commit();
			} else {
				utx.rollback();
				throw new ProjectException("Recurso no encontrado");
			}
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResourceActivity> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Resource.getAllResources");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de recursos");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResourceActivity> getInformationBasic() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Resource.getResourcesWithType");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de recursos con tipo");
		}
	}

	public void remove(Activity activity, ResourceActivity resource) throws ProjectException {
		try {
			UserTransaction utx = this.context.getUserTransaction();
			utx.begin();
			Activity act = this.em.find(Activity.class, activity.getId());

			ResourceActivity item = this.em.find(ResourceActivity.class, resource.getId());
			if (item != null) {
				act.getResources().remove(item);
				utx.commit();
			} else {
				utx.rollback();
				throw new ProjectException("Recurso no encontrado");
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