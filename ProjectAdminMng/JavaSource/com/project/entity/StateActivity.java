package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbStateActivity")
@NamedQueries({ @NamedQuery(name = "StateActivity.getAll", query = "SELECT r FROM StateActivity r") })
public class StateActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String state;

	public StateActivity() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public String getState() {
		return this.state;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "StateActivity [id=" + this.id + ", state=" + this.state + "]";
	}
}
