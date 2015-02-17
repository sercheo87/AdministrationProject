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

import com.project.dto.Summary;
import com.project.entity.Activity;
import com.project.entity.Beneficiary;
import com.project.entity.Project;
import com.project.exceptions.ProjectException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectService {

	@Resource
	private EJBContext context;
	@PersistenceContext
	private EntityManager em;

	public void add(Project project) throws ProjectException {

		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();
			Project wProject = null;
			if (project.getId() != null) {
				wProject = this.em.find(Project.class, project.getId());
				System.out.println("Objeto devuelto :" + wProject);
			}
			if (wProject == null) {
				wProject = new Project();
				wProject.setDateFinish(project.getDateFinish());
				wProject.setDateStart(project.getDateStart());
				wProject.setDescription(project.getDescription());
				wProject.setDuration(project.getDuration());
				wProject.setName(project.getName());
				wProject.setScope(project.getScope());
				wProject.setTarget(project.getTarget());
				this.em.persist(project);
			} else {
				wProject.setDateFinish(project.getDateFinish());
				wProject.setDateStart(project.getDateStart());
				wProject.setDescription(project.getDescription());
				wProject.setDuration(project.getDuration());
				wProject.setName(project.getName());
				wProject.setScope(project.getScope());
				wProject.setTarget(project.getTarget());
				this.em.merge(wProject);
			}

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

	public void addBeneficiary(Integer projectId, Integer beneficiaryId) throws ProjectException {
		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();
			Project project = this.em.find(Project.class, projectId);
			if (project == null) {
				throw new ProjectException("Proyecto no encontrado");
			}
			System.out.println("Project finded:" + project);

			Beneficiary beneficiary = this.em.find(Beneficiary.class, beneficiaryId);
			if (beneficiary == null) {
				throw new ProjectException("Beneficiario no encontrado");
			}
			System.out.println("Beneficiary finded:" + beneficiary);

			if (project.getBeneficiary().indexOf(beneficiary) < 0) {
				project.getBeneficiary().add(beneficiary);

				this.em.merge(project);

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
			} else {
				utx.rollback();
				throw new ProjectException("Beneficiario ya existente");
			}
		} catch (ProjectException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al agregar el beneficiario");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getActivities(Integer projectId) throws ProjectException {
		try {
			javax.persistence.Query qr = this.em.createNamedQuery("Project.getActivitiesByProject");
			qr.setParameter("idProject", projectId);

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAll() throws ProjectException {
		try {
			javax.persistence.Query qr = this.em.createNamedQuery("Project.getAll");
			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	public Project getProjectById(Integer idProject) throws ProjectException {
		try {
			Project project = this.em.find(Project.class, idProject);
			return project;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el proyecto");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectsByBeneficiary(Integer beneficiaryId) throws ProjectException {
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
	public List<Project> getProjectsResume() throws ProjectException {
		try {
			Query qr = this.em.createNamedQuery("Project.getResume");

			return qr.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Summary> getSummary(Integer IdProject) throws ProjectException {
		try {
			Query qrResponsible = this.em.createNamedQuery("Project.getSummaryResponsibles");
			qrResponsible.setParameter("idProject", IdProject);
			List<Summary> lsResponsibles = qrResponsible.getResultList();
			System.out.println(lsResponsibles);

			Query qrResources = this.em.createNamedQuery("Project.getSummaryResources");
			qrResources.setParameter("idProject", IdProject);
			List<Summary> lsResources = qrResources.getResultList();
			System.out.println(lsResources);

			for (Summary resource : lsResources) {
				for (Summary responsible : lsResponsibles) {
					if (resource.getId() == responsible.getId()) {
						resource.setTotalResponsibles(responsible.getTotalResponsibles());
						break;
					}
				}
			}

			return lsResources;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el listado de actividades");
		}
	}

	public void removeBeneficiary(Integer projectId, Integer beneficiaryId) throws ProjectException {
		UserTransaction utx = this.context.getUserTransaction();
		try {
			utx.begin();
			Project project = this.em.find(Project.class, projectId);
			if (project == null) {
				throw new ProjectException("Proyecto no encontrado");
			}

			Beneficiary beneficiary = this.em.find(Beneficiary.class, beneficiaryId);
			if (beneficiary == null) {
				throw new ProjectException("Beneficiario no encontrado");
			}

			System.out.println("Beneficiary finded:" + beneficiary);

			if (project.getBeneficiary().indexOf(beneficiary) < 0) {
				utx.rollback();
				throw new ProjectException("Beneficiario no encontrado");
			} else {
				project.getBeneficiary().remove(beneficiary);
				this.em.merge(project);
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
			}
		} catch (ProjectException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al eliminar el beneficiario");
		}
	}

}
