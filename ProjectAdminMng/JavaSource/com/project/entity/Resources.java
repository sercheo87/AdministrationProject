package com.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "tbResources")
@NamedQueries({ @NamedQuery(name = "Resources.getResourcesWithType", query = "SELECT r.id,r.type,r.typeResource.name FROM Resources r"), @NamedQuery(name = "Resources.getAllResources", query = "SELECT res FROM Resources res") })
public class Resources implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "type")
	private Integer type;

	@ManyToOne
	@JoinColumn(name = "id_typeResource")
	private TypeResource typeResource;

	public Resources() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @return the typeResource
	 */
	public TypeResource getTypeResource() {
		return typeResource;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @param typeResource
	 *            the typeResource to set
	 */
	public void setTypeResource(TypeResource typeResource) {
		this.typeResource = typeResource;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resources [id=" + id + ", type=" + type + ", typeResource=" + typeResource + "]";
	}
}
