package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


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

	@Column(name="id_competence",insertable=false, updatable=false)
	private int idCompetence;
	
	@OneToMany(mappedBy= "competence",cascade = CascadeType.ALL)
	private List<PersonCompetence> personCompetence;

	public Competence() {
	}

	public String getCompetenceName() {
		return this.competenceName;
	}

	public void setCompetenceName(String competenceName) {
		this.competenceName = competenceName;
	}

	public int getIdCompetence() {
		return this.idCompetence;
	}

	public void setIdCompetence(int idCompetence) {
		this.idCompetence = idCompetence;
	}

}