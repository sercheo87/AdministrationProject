package com.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.entity.Job;
import com.project.exceptions.ProjectException;

@Stateless
public class JobService {
	@PersistenceContext
	private EntityManager em;

	public void add(Job job) throws ProjectException {
		try {
			this.em.persist(job);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el cargo del responsable");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAll() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Job.getAll");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de los cargos de los responsables");
		}
	}

	public void remove(Job job) throws ProjectException {
		try {
			Job item = this.em.find(Job.class, job.getId());
			this.em.remove(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al borrar el cargo del responsable");
		}
	}

	public void update(Job job) throws ProjectException {
		try {
			Job item = this.em.find(Job.class, job.getId());
			item = job;

			this.em.merge(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al actualizar el cargo del responsable");
		}
	}
}
