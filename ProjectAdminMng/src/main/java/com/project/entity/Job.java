package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbJob")
@NamedQueries({ @NamedQuery(name = "Job.getAll", query = "SELECT j FROM Job j") })
public class Job {
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idJob")
	private Integer id;
	private String job;

	public String getDescription() {
		return this.description;
	}

	public Integer getId() {
		return this.id;
	}

	public String getJob() {
		return this.job;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Job [description=" + this.description + ", id=" + this.id + ", job=" + this.job + "]";
	}
}
