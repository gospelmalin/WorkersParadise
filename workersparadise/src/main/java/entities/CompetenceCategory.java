package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competence_category database table.
 * 
 */
@Entity
@Table(name="competence_category")
@NamedQuery(name="CompetenceCategory.findAll", query="SELECT c FROM CompetenceCategory c")
public class CompetenceCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_competence_category")
	private int idCompetenceCategory;

	@Column(name="competence_category_name")
	private String competenceCategoryName;

	//bi-directional many-to-one association to Competence
	@OneToMany(mappedBy="competenceCategory")
	private List<Competence> competences;

	public CompetenceCategory() {
	}

	public int getIdCompetenceCategory() {
		return this.idCompetenceCategory;
	}

	public void setIdCompetenceCategory(int idCompetenceCategory) {
		this.idCompetenceCategory = idCompetenceCategory;
	}

	public String getCompetenceCategoryName() {
		return this.competenceCategoryName;
	}

	public void setCompetenceCategoryName(String competenceCategoryName) {
		this.competenceCategoryName = competenceCategoryName;
	}

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public Competence addCompetence(Competence competence) {
		getCompetences().add(competence);
		competence.setCompetenceCategory(this);

		return competence;
	}

	public Competence removeCompetence(Competence competence) {
		getCompetences().remove(competence);
		competence.setCompetenceCategory(null);

		return competence;
	}

}