package entities;

import java.io.Serializable;
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

	public void setPerson(Person person) {
		this.person = person;
	}

}