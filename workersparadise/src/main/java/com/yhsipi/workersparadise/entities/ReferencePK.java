package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reference database table.
 * 
 */
@Embeddable
public class ReferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_reference")
	private int idReference;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public ReferencePK() {
	}
	public int getIdReference() {
		return this.idReference;
	}
	public void setIdReference(int idReference) {
		this.idReference = idReference;
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
		if (!(other instanceof ReferencePK)) {
			return false;
		}
		ReferencePK castOther = (ReferencePK)other;
		return 
			(this.idReference == castOther.idReference)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idReference;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}