package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the webpage database table.
 * 
 */
@Embeddable
public class WebpagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_webpage")
	private int idWebpage;

	@Column(name="id_person", insertable=false, updatable=false)
	private int idPerson;

	public WebpagePK() {
	}
	public int getIdWebpage() {
		return this.idWebpage;
	}
	public void setIdWebpage(int idWebpage) {
		this.idWebpage = idWebpage;
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
		if (!(other instanceof WebpagePK)) {
			return false;
		}
		WebpagePK castOther = (WebpagePK)other;
		return 
			(this.idWebpage == castOther.idWebpage)
			&& (this.idPerson == castOther.idPerson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idWebpage;
		hash = hash * prime + this.idPerson;
		
		return hash;
	}
}