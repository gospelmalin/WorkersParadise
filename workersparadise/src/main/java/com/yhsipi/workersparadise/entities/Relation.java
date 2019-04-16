package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the relation database table.
 * 
 */
@Entity
@Table(name="relation")
@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r")
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_relation")
	private int idRelation;

	@Column(name="relation_name")
	private String relationName;

	//bi-directional many-to-one association to Reference
	@OneToMany(mappedBy="relation")
	private List<Reference> references;

	public Relation() {
	}

	public int getIdRelation() {
		return this.idRelation;
	}

	public void setIdRelation(int idRelation) {
		this.idRelation = idRelation;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public List<Reference> getReferences() {
		return this.references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public Reference addReference(Reference reference) {
		getReferences().add(reference);
		reference.setRelation(this);

		return reference;
	}

	public Reference removeReference(Reference reference) {
		getReferences().remove(reference);
		reference.setRelation(null);

		return reference;
	}

}