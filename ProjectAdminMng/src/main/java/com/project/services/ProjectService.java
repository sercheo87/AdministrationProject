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

import com.project.entity.Activity;
import com.project.entity.Beneficiary;
import com.project.exceptions.ProjectException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectService {

	@Resource
	private EJBContext context;
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Activity> getAll() throws ProjectException {
		try {
			javax.persistence.Query qr = this.em.createNamedQuery("Project.getAll");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getProjectsByBeneficiary(Integer beneficiaryId) throws ProjectException {
		try {
			Beneficiary beneficiary = this.em.find(Beneficiary.class, beneficiaryId);

			if (beneficiary != null) {
				Query qr = this.em.createNamedQuery("Project.getProjectByName");
				qr.setParameter("idBeneficiary", beneficiaryId);

				return qr.getResultList();
			} else {
				throw new ProjectException("No se encuentra al beneficiario");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getProjectsResume() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Project.getResume");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

}
