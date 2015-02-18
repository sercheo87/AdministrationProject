package com.project.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.dto.SummaryBeneficiary;
import com.project.entity.Beneficiary;
import com.project.exceptions.ProjectException;

@Stateless
public class BeneficiaryService {
	@PersistenceContext
	private EntityManager em;

	public void add(Beneficiary beneficiary) throws ProjectException {
		try {
			this.em.persist(beneficiary);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el beneficiario");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Beneficiary> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Beneficiary.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los beneficiarios");
		}
	}

	@SuppressWarnings("unchecked")
	public List<SummaryBeneficiary> getSummary() {
		List<SummaryBeneficiary> collection = new ArrayList<SummaryBeneficiary>();
		List<Beneficiary> listBeneficiary = this.em.createQuery("select b from Beneficiary b").getResultList();
		for (Beneficiary beneficiary : listBeneficiary) {
			SummaryBeneficiary summary = new SummaryBeneficiary();
			summary.setBeneficiary(beneficiary);

			Query qr = this.em.createNamedQuery("Project.getProjectByName");
			qr.setParameter("idBeneficiary", beneficiary.getId());
			summary.setTotalProjects(qr.getResultList().size());

			collection.add(summary);
		}
		return collection;
	}

	public void remove(Beneficiary beneficiary) throws ProjectException {
		try {
			Beneficiary item = this.em.find(Beneficiary.class, beneficiary.getId());
			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el beneficiario");
		}
	}

	public void update(Integer idBeneficiary, Beneficiary beneficiary) throws ProjectException {
		try {
			Beneficiary item = this.em.find(Beneficiary.class, idBeneficiary);
			item.setCommunity(beneficiary.getCommunity());
			item.setName(beneficiary.getName());
			item.setNumberOfCitizens(beneficiary.getNumberOfCitizens());
			item.setPresident(beneficiary.getPresident());
			item.setUid(beneficiary.getUid());

			this.em.merge(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar el estado de la actividad");
		}

	}
}