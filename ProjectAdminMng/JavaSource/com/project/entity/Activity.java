package com.project.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbActivity")
@NamedQueries({ @NamedQuery(name = "Activity.getAll", query = "SELECT act FROM Activity act") })
public class Activity {
	private Date dateFinish;
	private Date dateStart;
	private String description;
	private Integer durationDays;
	private Integer durationHours;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "stateActivity_id")
	private StateActivity state;

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

	public void setState(StateActivity state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Activity [dateFinish=" + this.dateFinish + ", dateStart=" + this.dateStart + ", description="
				+ this.description + ", durationDays=" + this.durationDays + ", durationHours=" + this.durationHours
				+ ", id=" + this.id + ", state=" + this.state + "]";
	}

}
