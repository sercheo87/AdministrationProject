package com.project.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tbProject")
@NamedQueries({
	@NamedQuery(name = "Project.getAll", query = "SELECT p FROM Project p"),
	@NamedQuery(name = "Project.getResume", query = "SELECT NEW com.project.entity.Project(p.dateFinish, p.dateStart, p.description, p.id, p.name) FROM Project p"),
	@NamedQuery(name = "Project.getProjectByName", query = "SELECT p FROM Project p JOIN p.beneficiary b where b.id = :idBeneficiary"),
	@NamedQuery(name = "Project.getSummaryResponsibles", query = "SELECT new com.project.dto.Summary(a.id, a.description, 0, size(r.id)) FROM Project p join p.activities a left join a.responsibles r where p.id=:idProject group by a.id, a.description order by a.id,a.description"),
	@NamedQuery(name = "Project.getSummaryResources", query = "SELECT new com.project.dto.Summary(a.id, a.description, size(r.id), 0) FROM Project p join p.activities a left join a.resources r where p.id=:idProject group by a.id, a.description order by a.id,a.description"),
	@NamedQuery(name = "Project.getActivitiesByProject", query = "SELECT p.activities FROM Project p where p.id = :idProject") })
public class Project {
	@OneToMany(targetEntity = Activity.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Activity> activities;

	@ManyToMany(targetEntity = Beneficiary.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Beneficiary> beneficiary;

	@Column(name = "dateFinish")
	private Date dateFinish;

	@Column(name = "dateStart")
	private Date dateStart;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "duration")
	private BigDecimal duration;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "scope", length = 2000)
	private String scope;

	@Column(name = "target", length = 2000)
	private String target;

	public Project() {
		super();
	}

	public Project(Date dateFinish, Date dateStart, String description, Integer id, String name) {
		super();
		this.dateFinish = dateFinish;
		this.dateStart = dateStart;
		this.description = description;
		this.id = id;
		this.name = name;
	}

	public Project(List<Activity> activities, List<Beneficiary> beneficiary, Date dateFinish, Date dateStart,
			String description, BigDecimal duration, Integer id, String name, String scope, String target) {
		super();
		this.activities = activities;
		this.beneficiary = beneficiary;
		this.dateFinish = dateFinish;
		this.dateStart = dateStart;
		this.description = description;
		this.duration = duration;
		this.id = id;
		this.name = name;
		this.scope = scope;
		this.target = target;
	}

	public List<Activity> getActivities() {
		return this.activities;
	}

	public List<Beneficiary> getBeneficiary() {
		return this.beneficiary;
	}

	public Date getDateFinish() {
		return this.dateFinish;
	}

	/**
	 * @return the dateStart
	 */
	public Date getDateStart() {
		return this.dateStart;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the duration
	 */
	public BigDecimal getDuration() {
		return this.duration;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return this.scope;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return this.target;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public void setBeneficiary(List<Beneficiary> beneficiary) {
		this.beneficiary = beneficiary;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	/**
	 * @param dateStart
	 *            the dateStart to set
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Project [activities=" + this.activities + ", beneficiary=" + this.beneficiary + ", dateStart="
				+ this.dateStart + ", description=" + this.description + ", duration=" + this.duration + ", id="
				+ this.id + ", name=" + this.name + ", scope=" + this.scope + ", target=" + this.target + "]";
	}
}