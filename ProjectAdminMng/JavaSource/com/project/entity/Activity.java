package com.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbActivity")
@NamedQueries({ @NamedQuery(name = "Activity.getAll", query = "SELECT a FROM Activity a ") })
public class Activity {
	@Temporal(TemporalType.DATE)
	private Date dateFinish;

	@Temporal(TemporalType.DATE)
	private Date dateStart;

	private String description;
	private Integer durationDays;
	private Integer durationHours;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "activity", orphanRemoval = true)
	private List<ResourceActivity> resources;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_stateActivity")
	private StateActivity state;

	public Activity() {
		super();
	}

	public Activity(List<ResourceActivity> resources) {
		super();
		this.resources = resources;
	}

	public Date getDateFinish() {
		return this.dateFinish;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public String getDescription() {
		return this.description;
	}

	public Integer getDurationDays() {
		return this.durationDays;
	}

	public Integer getDurationHours() {
		return this.durationHours;
	}

	public Integer getId() {
		return this.id;
	}

	public List<ResourceActivity> getResources() {
		return this.resources;
	}

	public StateActivity getState() {
		return this.state;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setResources(List<ResourceActivity> resources) {
		this.resources = resources;
	}

	public void setState(StateActivity state) {
		this.state = state;
	}
}
