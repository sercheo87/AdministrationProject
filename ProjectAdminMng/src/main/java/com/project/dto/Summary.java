package com.project.dto;

public class Summary {
	private Integer id;

	private String name;
	private Integer totalResources;

	private Integer totalResponsibles;

	public Summary() {
		super();
	}

	public Summary(Integer id, String name, Integer totalResources, Integer totalResponsibles) {
		super();
		this.id = id;
		this.name = name;
		this.totalResources = totalResources;
		this.totalResponsibles = totalResponsibles;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getTotalResources() {
		return this.totalResources;
	}

	public Integer getTotalResponsibles() {
		return this.totalResponsibles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTotalResources(Integer totalResources) {
		this.totalResources = totalResources;
	}

	public void setTotalResponsibles(Integer totalResponsibles) {
		this.totalResponsibles = totalResponsibles;
	}

	@Override
	public String toString() {
		return "Summary [id=" + this.id + ", name=" + this.name + ", totalResources=" + this.totalResources
		        + ", totalResponsibles=" + this.totalResponsibles + "]";
	}
}
