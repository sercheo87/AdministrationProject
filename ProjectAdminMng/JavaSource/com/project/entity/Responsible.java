package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbResponsible")
public class Responsible {

	@Column
	private String address;

	@Column
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idResponsible")
	private Integer id;

	@Column
	private String lastName;

	@Column
	private String name;

	@Column
	private String phone;

	@ManyToOne
	@JoinColumn(name = "id_stateResponsible")
	private StateResponsible stateResponsible;

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public StateResponsible getStateResponsible() {
		return this.stateResponsible;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStateResponsible(StateResponsible stateResponsible) {
		this.stateResponsible = stateResponsible;
	}

	@Override
	public String toString() {
		return "Responsible [address=" + this.address + ", email=" + this.email + ", id=" + this.id + ", lastName="
		        + this.lastName + ", name=" + this.name + ", phone=" + this.phone + "]";
	}
}
