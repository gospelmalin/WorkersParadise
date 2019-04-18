package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import java.util.List;

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

}