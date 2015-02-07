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
@Table(name = "tbBeneficiary")
@NamedQueries({ @NamedQuery(name = "Beneficiary.getAll", query = "SELECT b FROM Beneficiary b") })
public class Beneficiary {

	@Column
	private String community;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBeneficiary")
	private Integer id;

	@Column
	private String name;

	@Column
	private Integer numberOfCitizens;

	@Column
	private String president;

	@Column
	private String uid;

	public String getCommunity() {
		return this.community;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getNumberOfCitizens() {
		return this.numberOfCitizens;
	}

	public String getPresident() {
		return this.president;
	}

	public String getUid() {
		return this.uid;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberOfCitizens(Integer numberOfCitizens) {
		this.numberOfCitizens = numberOfCitizens;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Beneficiary [community=" + this.community + ", id=" + this.id + ", name=" + this.name
		        + ", numberOfCitizens=" + this.numberOfCitizens + ", president=" + this.president + ", uid=" + this.uid
		        + "]";
	}

}
