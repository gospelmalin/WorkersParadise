package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the phone database table.
 * 
 */
@Embeddable
public class PhonePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_phone")
	private int idPhone;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public PhonePK() {
	}
	public int getIdPhone() {
		return this.idPhone;
	}
	public void setIdPhone(int idPhone) {
		this.idPhone = idPhone;
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
		if (!(other instanceof PhonePK)) {
			return false;
		}
		PhonePK castOther = (PhonePK)other;
		return 
			(this.idPhone == castOther.idPhone)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPhone;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}