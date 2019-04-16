package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the professional_experience database table.
 * 
 */
@Embeddable
public class ProfessionalExperiencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_prof_experience")
	private int idProfExperience;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public ProfessionalExperiencePK() {
	}
	public int getIdProfExperience() {
		return this.idProfExperience;
	}
	public void setIdProfExperience(int idProfExperience) {
		this.idProfExperience = idProfExperience;
	}
	public int getIdPerson() {
		return this.idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProfessionalExperiencePK)) {
			return false;
		}
		ProfessionalExperiencePK castOther = (ProfessionalExperiencePK)other;
		return 
			(this.idProfExperience == castOther.idProfExperience)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProfExperience;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}