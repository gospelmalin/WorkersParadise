package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="email")
@DynamicUpdate
@NamedQuery(name="Email.findAll", query="SELECT e FROM Email e")
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmailPK id;

	@Column(name="email")
	private String emailAddress;
	
	@Column(name="primary_email")
	private boolean primaryEmail;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person", insertable=false, updatable=false)
	private Person person;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type type;

	public Email() {
	}

	public EmailPK getId() {
		return this.id;
	}

	public void setId(EmailPK id) {
		this.id = id;
	}
	/*
	public String getEmail() {
		return this.emailAddress;
	}
	
	public void setEmail(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmail() {
		return this.emailAddress;
	}
	*/
	// TODO testar ta bort ovan och lägga till nedan
	public String getEmail() {
		emailAddress = getEmailAddress();
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	

	public void setEmail(String emailAddress) {
		this.emailAddress = emailAddress;
	}
		
	
	
	

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(boolean primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	// This format is used in profile.html. Do not change without handling profile.html
	@Override
	public String toString() {
	return emailAddress;

	}

	
}