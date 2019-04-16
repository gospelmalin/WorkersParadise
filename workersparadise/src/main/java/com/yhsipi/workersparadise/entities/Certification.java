package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the certification database table.
 * 
 */
@Entity
@Table(name="certification")
@NamedQuery(name="Certification.findAll", query="SELECT c FROM Certification c")
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificationPK id;

	@Column(name="certification_description")
	private String certificationDescription;

	@Column(name="certification_name")
	private String certificationName;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	public Certification() {
	}

	public CertificationPK getId() {
		return this.id;
	}

	public void setId(CertificationPK id) {
		this.id = id;
	}

	public String getCertificationDescription() {
		return this.certificationDescription;
	}

	public void setCertificationDescription(String certificationDescription) {
		this.certificationDescription = certificationDescription;
	}

	public String getCertificationName() {
		return this.certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}