package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the address database table.
 * 
 */
@Embeddable
public class AddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_address")
	private int idAddress;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public AddressPK() {
	}
	public int getIdAddress() {
		return this.idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
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
		if (!(other instanceof AddressPK)) {
			return false;
		}
		AddressPK castOther = (AddressPK)other;
		return 
			(this.idAddress == castOther.idAddress)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idAddress;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}