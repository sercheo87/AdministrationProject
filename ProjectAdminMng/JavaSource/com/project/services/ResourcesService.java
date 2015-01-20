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
import javax.transaction.UserTransaction;

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

	public void add(ResourceActivity resource) throws ProjectException {
		try {
			System.out.println("Recurso a ingresar");
			System.out.println(resource);

			// Activity activity = this.em.find(Activity.class, resource.getActivity().getId());
			TypeResource typeResource = this.em.find(TypeResource.class, resource.getTypeResource().getId());

			System.out.println("Recurso filtrado");
			System.out.println(resource);

			resource.setTypeResource(typeResource);
			// resource.setActivity(activity);

			this.em.persist(resource);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el recurso");
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

	public void remove(ResourceActivity resource) throws ProjectException {
		try {
			UserTransaction utx = this.context.getUserTransaction();
			utx.begin();
			ResourceActivity item = this.em.find(ResourceActivity.class, resource.getId());
			System.out.println("Recurso a Eliminar:" + item);
			this.em.remove(item);
			this.em.getTransaction().commit();
			utx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el recurso de la actividad");
		}

	}
}