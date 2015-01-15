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
@NamedQueries({ @NamedQuery(name = "Resource.getResourcesWithType", query = "SELECT r.id, r.quantity FROM Resource r"),
        @NamedQuery(name = "Resource.getAllResources", query = "SELECT res FROM Resource res") })
public class Resource implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "re_id_activity")
	private Activity activity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "id_typeResource")
	private TypeResource typeResource;

	public Resource() {
		super();
	}

	public Resource(Integer id, Integer quantity, TypeResource typeResource) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.typeResource = typeResource;
	}

	public Activity getActivity() {
		return this.activity;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * @return the typeResource
	 */
	public TypeResource getTypeResource() {
		return this.typeResource;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param typeResource
	 *            the typeResource to set
	 */
	public void setTypeResource(TypeResource typeResource) {
		this.typeResource = typeResource;
	}

}
