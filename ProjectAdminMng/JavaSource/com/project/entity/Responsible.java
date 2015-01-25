package com.project.entity;

import java.io.Serializable;

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
public class Responsible implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_activity")
	private Activity activity;

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

	public Responsible() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responsible(String address, String email, Integer id, String lastName, String name, String phone) {
		super();
		this.address = address;
		this.email = email;
		this.id = id;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
	}

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

	@Override
	public String toString() {
		return "Responsible [address=" + this.address + ", email=" + this.email + ", id=" + this.id + ", lastName="
		        + this.lastName + ", name=" + this.name + ", phone=" + this.phone + "]";
	}
}
