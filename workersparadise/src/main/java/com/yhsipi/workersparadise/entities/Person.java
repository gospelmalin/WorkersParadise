package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_person")
	private int idPerson;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<Address> address;
	
	//bi-directional one-to-one association to About 
	@OneToOne(mappedBy="person", cascade={CascadeType.ALL}) 
	private About about;
	
	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public int getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {	
		this.phones = phones;
	}
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Person() {
	}
	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", birthdate=" + birthdate + ", firstName=" + firstName + ", gender="
				+ gender + ", lastName=" + lastName + ", middleName=" + middleName + ", phones=" + phones + "]";
	}
	
	public String getFullName() {
		return this.firstName + " "+ this.lastName;
	}

}