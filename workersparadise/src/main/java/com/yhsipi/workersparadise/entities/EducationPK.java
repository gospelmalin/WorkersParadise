package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the education database table.
 * 
 */
@Embeddable
public class EducationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_education")
	private int idEducation;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public EducationPK() {
	}
	public int getIdEducation() {
		return this.idEducation;
	}
	public void setIdEducation(int idEducation) {
		this.idEducation = idEducation;
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
		if (!(other instanceof EducationPK)) {
			return false;
		}
		EducationPK castOther = (EducationPK)other;
		return 
			(this.idEducation == castOther.idEducation)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEducation;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}