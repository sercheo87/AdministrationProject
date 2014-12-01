package com.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbProject")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name", length = 255)
	private String name;
	@Column(name = "description", length = 1000)
	private String description;
	@Column(name = "dateStart")
	private Date dateStart;
	@Column(name = "duration")
	private BigDecimal duration;
	@Column(name = "scope", length = 2000)
	private String scope;
	@Column(name = "target", length = 2000)
	private String target;
}