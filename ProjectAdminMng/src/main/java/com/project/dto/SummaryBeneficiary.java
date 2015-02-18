package com.project.dto;

import com.project.entity.Beneficiary;

public class SummaryBeneficiary {
	private Beneficiary beneficiary;
	private Integer totalProjects;

	public SummaryBeneficiary() {
		super();
	}

	public SummaryBeneficiary(Beneficiary beneficiary, Integer totalProjects) {
		super();
		this.beneficiary = beneficiary;
		this.totalProjects = totalProjects;
	}

	public Beneficiary getBeneficiary() {
		return this.beneficiary;
	}

	public Integer getTotalProjects() {
		return this.totalProjects;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public void setTotalProjects(Integer totalProjects) {
		this.totalProjects = totalProjects;
	}

	@Override
	public String toString() {
		return "SummaryBeneficiary [beneficiary=" + this.beneficiary + ", totalProjects=" + this.totalProjects + "]";
	}
}
