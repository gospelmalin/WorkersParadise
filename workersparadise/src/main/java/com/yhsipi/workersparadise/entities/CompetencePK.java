package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the competence database table.
 * 
 */
@Embeddable
public class CompetencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_competence")
	private int idCompetence;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public CompetencePK() {
	}
	public int getIdCompetence() {
		return this.idCompetence;
	}
	public void setIdCompetence(int idCompetence) {
		this.idCompetence = idCompetence;
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
		if (!(other instanceof CompetencePK)) {
			return false;
		}
		CompetencePK castOther = (CompetencePK)other;
		return 
			(this.idCompetence == castOther.idCompetence)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCompetence;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}