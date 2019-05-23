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
 * The persistent class for the phone database table.
 * 
 */
@Entity
@Table(name="phone")
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhonePK id;

	@Column(name="country_prefix")
	private String countryPrefix;

	
	@Column(name="id_phone_category")
	private int idPhoneCategory;

	@Column(name="phone_number")
	private int phoneNumber;

	@Column(name="primary_contact_number")
	private boolean primaryContactNumber;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type type;

	public Phone() {
	}

	public PhonePK getId() {
		return this.id;
	}

	public void setId(PhonePK id) {
		this.id = id;
	}

	public String getCountryPrefix() {
		return this.countryPrefix;
	}

	public void setCountryPrefix(String countryPrefix) {
		this.countryPrefix = countryPrefix;
	}

	public int getIdPhoneCategory() {
		return this.idPhoneCategory;
	}

	public void setIdPhoneCategory(int idPhoneCategory) {
		this.idPhoneCategory = idPhoneCategory;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getPrimaryContactNumber() {
		return this.primaryContactNumber;
	}

	public void setPrimaryContactNumber(boolean primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Optional<Person> person) {
		if(person != null) {
			this.person = person.get();
		}
	}
	
	public void setPersonPerson(Person person) {
		this.person = person;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getTypeNo() {
		return type.getTypeNo();
	}
	@Override
	public String toString() {
		return countryPrefix + " " + phoneNumber;
	}
	
	public int getIdPhone() {
		return id.getIdPhone();
	}
	
	public int getIdPerson() {
		return id.getIdPerson();
	}
	
}