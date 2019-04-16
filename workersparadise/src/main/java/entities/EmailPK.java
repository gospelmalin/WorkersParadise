package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the email database table.
 * 
 */
@Embeddable
public class EmailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_email")
	private int idEmail;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public EmailPK() {
	}
	public int getIdEmail() {
		return this.idEmail;
	}
	public void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
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
		if (!(other instanceof EmailPK)) {
			return false;
		}
		EmailPK castOther = (EmailPK)other;
		return 
			(this.idEmail == castOther.idEmail)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEmail;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}