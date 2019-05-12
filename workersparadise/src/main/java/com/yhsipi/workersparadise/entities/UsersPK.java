package com.yhsipi.workersparadise.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the users database table.
 * 
 */
@Embeddable
public class UsersPK implements Serializable{
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@Column(name="id_user")
		private int idUser;

		@Column(name="id_person", insertable=false, updatable=false)
		private int idPerson;

		public UsersPK() {
		}
		public int getIdUser() {
			return this.idUser;
		}
		public void setIdUser(int idUser) {
			this.idUser = idUser;
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
			if (!(other instanceof UsersPK)) {
				return false;
			}
			UsersPK castOther = (UsersPK)other;
			return 
				(this.idUser == castOther.idUser)
				&& (this.idPerson == castOther.idPerson);
		}

		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + this.idUser;
			hash = hash * prime + this.idPerson;
			
			return hash;
		}
}
