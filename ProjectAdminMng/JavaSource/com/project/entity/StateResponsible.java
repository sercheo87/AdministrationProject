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
@Table(name = "tbStateResponsible")
@NamedQueries({ @NamedQuery(name = "StateResponsible.getAll", query = "SELECT s FROM StateResponsible s") })
public class StateResponsible {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStateResponsible")
	private Integer id;

	@Column
	private String State;

	public Integer getId() {
		return this.id;
	}

	public String getState() {
		return this.State;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setState(String state) {
		this.State = state;
	}

	@Override
	public String toString() {
		return "StateResponsible [id=" + this.id + ", State=" + this.State + "]";
	}

}
