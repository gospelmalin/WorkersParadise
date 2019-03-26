package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the certification database table.
 * 
 */
@Embeddable
public class CertificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_certification")
	private int idCertification;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public CertificationPK() {
	}
	public int getIdCertification() {
		return this.idCertification;
	}
	public void setIdCertification(int idCertification) {
		this.idCertification = idCertification;
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
		if (!(other instanceof CertificationPK)) {
			return false;
		}
		CertificationPK castOther = (CertificationPK)other;
		return 
			(this.idCertification == castOther.idCertification)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCertification;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}