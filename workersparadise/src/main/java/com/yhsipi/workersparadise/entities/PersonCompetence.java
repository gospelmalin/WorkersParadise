package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the person_competence database table.
 * 
 */
@Entity
@Table(name="person_competence")
@NamedQuery(name="PersonCompetence.findAll", query="SELECT p FROM PersonCompetence p")
public class PersonCompetence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_person_competence")
	private int idPersonCompetence;
	
	@ManyToOne
	@JoinColumn(name="id_competence")
	Competence competence;

	@Override
	public String toString() {
		return "PersonCompetence [idPersonCompetence=" + idPersonCompetence + ", competence=" + competence + ", person="
				+ person + "]";
	}

	@ManyToOne
	@JoinColumn(name="id_person")
	Person person;

	public boolean equals(PersonCompetence obj) {
		if (this.person.getIdPerson() == obj.person.getIdPerson()) {
			return true;
		}
			return false;
	}


	public PersonCompetence() {
	}


	public Competence getCompetence() {
		return competence;
	}



	public void setCompetence(Competence competence) {
		this.competence = competence;
	}



	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}



	public int getIdPersonCompetence() {
		return this.idPersonCompetence;
	}

	public void setIdPersonCompetence(int idPersonCompetence) {
		this.idPersonCompetence = idPersonCompetence;
	}

}