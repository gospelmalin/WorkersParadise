package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the competence database table.
 * 
 */
@Entity
@Table(name="competence")
@NamedQuery(name="Competence.findAll", query="SELECT c FROM Competence c")
public class Competence implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompetencePK id;

	@Column(name="competence_name")
	private String competenceName;

	//bi-directional many-to-one association to CompetenceCategory
	@ManyToOne
	@JoinColumn(name="id_competence_category")
	private CompetenceCategory competenceCategory;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	public Competence() {
	}

	public CompetencePK getId() {
		return this.id;
	}

	public void setId(CompetencePK id) {
		this.id = id;
	}

	public String getCompetenceName() {
		return this.competenceName;
	}

	public void setCompetenceName(String competenceName) {
		this.competenceName = competenceName;
	}

	public CompetenceCategory getCompetenceCategory() {
		return this.competenceCategory;
	}

	public void setCompetenceCategory(CompetenceCategory competenceCategory) {
		this.competenceCategory = competenceCategory;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Optional<Person> person) {
		if(person != null) {
			this.person = person.get();
		}
	}

	public int getIdPerson() {
		return person.getIdPerson();
	}
}