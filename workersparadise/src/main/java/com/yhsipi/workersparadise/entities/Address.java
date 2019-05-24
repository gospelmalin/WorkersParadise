package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AddressPK id;

	private String city;

	@Column(name="CO")
	private String co;

	private String county;

	@Column(name="street_address")
	private String streetAddress;

	@Column(name="zip_code")
	private int zipCode;
	
	@Column(name="primary_address")
	private boolean primaryAddress;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type type;

	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", co=" + co + ", county=" + county + ", streetAddress="
				+ streetAddress + ", zipCode=" + zipCode + ", type=" + type + ", person=" + person + "]";
	}

	public Address() {
	}

	public AddressPK getId() {
		return this.id;
	}

	public void setId(AddressPK id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCo() {
		return this.co;
	}
	
	public String getCoWithCOForProfile() {
		return this.co != null ? "C/o " + this.co + " ·" : null;
	}


	public void setCo(String co) {
		this.co = co;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(boolean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	public void setPerson(Optional<Person> person) {

		if (person != null) {
			
			this.person = person.get();
		}
		
	}
	
	public int getPersonId() {
		return person.getIdPerson();
	}
}