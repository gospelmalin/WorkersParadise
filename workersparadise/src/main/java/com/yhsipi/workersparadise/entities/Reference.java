package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reference database table.
 * 
 */
@Entity
@Table(name="reference")
@NamedQuery(name="Reference.findAll", query="SELECT r FROM Reference r")
public class Reference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReferencePK id;

	@Column(name="relation_description")
	private String relationDescription;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person1;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_reference_person_for")
	private Person person2;

	//bi-directional many-to-one association to Relation
	@ManyToOne
	@JoinColumn(name="id_relation")
	private Relation relation;

	public Reference() {
	}

	public ReferencePK getId() {
		return this.id;
	}

	public void setId(ReferencePK id) {
		this.id = id;
	}

	public String getRelationDescription() {
		return this.relationDescription;
	}

	public void setRelationDescription(String relationDescription) {
		this.relationDescription = relationDescription;
	}

	public Person getPerson1() {
		return this.person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Person getPerson2() {
		return this.person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}