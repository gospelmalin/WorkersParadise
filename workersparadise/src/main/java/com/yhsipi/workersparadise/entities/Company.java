package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="company")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_company")
	private int idCompany;

	@Column(name="company_description")
	private String companyDescription;

	@Column(name="company_name")
	private String companyName;

	//bi-directional many-to-one association to ProfessionalExperience
	@OneToMany(mappedBy="company")
	private List<ProfessionalExperience> professionalExperiences;

	public Company() {
	}

	public int getIdCompany() {
		return this.idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getCompanyDescription() {
		return this.companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<ProfessionalExperience> getProfessionalExperiences() {
		return this.professionalExperiences;
	}

	public void setProfessionalExperiences(List<ProfessionalExperience> professionalExperiences) {
		this.professionalExperiences = professionalExperiences;
	}

	public ProfessionalExperience addProfessionalExperience(ProfessionalExperience professionalExperience) {
		getProfessionalExperiences().add(professionalExperience);
		professionalExperience.setCompany(this);

		return professionalExperience;
	}

	public ProfessionalExperience removeProfessionalExperience(ProfessionalExperience professionalExperience) {
		getProfessionalExperiences().remove(professionalExperience);
		professionalExperience.setCompany(null);

		return professionalExperience;
	}

}