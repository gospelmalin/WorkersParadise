package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the about database table.
 * 
 */
@Entity
@Table(name="about")
@NamedQuery(name="About.findAll", query="SELECT a FROM About a")
public class About implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_about")
	private int idAbout;

	@Column(name="about_summary")
	private String aboutSummary;

	@Column(name="id_person",insertable=false, updatable=false)
	private int idPerson;

	//bi-directional one-to-one association to Person 
	@OneToOne 
	@JoinColumn(name="id_person", referencedColumnName="id_person") 
	private Person person;
	
	private String interests;

	public About() {
	}

	public int getIdAbout() {
		return this.idAbout;
	}

	public void setIdAbout(int idAbout) {
		this.idAbout = idAbout;
	}

	public String getAboutSummary() {
		return this.aboutSummary;
	}

	public void setAboutSummary(String aboutSummary) {
		this.aboutSummary = aboutSummary;
	}

	public int getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

}