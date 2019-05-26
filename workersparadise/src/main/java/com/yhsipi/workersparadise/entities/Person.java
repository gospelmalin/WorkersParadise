package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

//import org.hibernate.annotations.Cascade;


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
	
	@OneToMany(mappedBy= "person", cascade = CascadeType.ALL)
	//@Cascade(org.hibernate.annotations.CascadeType.ALL) //TODO check
	private List<Email> emails;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<Webpage> webpage;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<Education> education;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<Certification> certification;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<PersonCompetence> personCompetence;
	
	@OneToMany(mappedBy= "person",cascade = CascadeType.ALL)
	private List<ProfessionalExperience> profExperience;
	
	//bi-directional one-to-one association to About 
	@OneToOne(mappedBy="person") 
	private About about;


	@Lob
	@Column(name="image")
	private byte[] image;


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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
	
	public List<Email> getEmail() {
		return emails;
	}

	public void setEmail(List<Email> emails) {
		this.emails = emails;
	}
	
	public List<Webpage> getWebpage() {
		return webpage;
	}

	public void setWebpage(List<Webpage> webpage) {
		this.webpage = webpage;
	}


	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}
	
	public List<Certification> getCertification() {
		return certification;
	}

	public void setCertification(List<Certification> certification) {
		this.certification = certification;
	}

	
	public List<ProfessionalExperience> getProfExperience() {
		return profExperience;
	}

	public void setProfExperience(List<ProfessionalExperience> profExperience) {
		this.profExperience = profExperience;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<PersonCompetence> getPersonCompetence() {
		return personCompetence;
	}

	public void setPersonCompetence(List<PersonCompetence> personCompetence) {
		this.personCompetence = personCompetence;
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
	
	public String getPrimaryPhoneNumber() {
		for (Phone phone : phones) {
			if (phone.getPrimaryContactNumber()) {
				return phone.toString();
			}		
		}
		return "";		
	}
	
	
	public String getPrimaryEmail() {
		for (Email email : emails) {
			if (email.isPrimaryEmail()) {
				return email.toString();
			}		
		}
		return "";		
	}
	
	public String getPrimaryStreetAddress() {
		for (Address address : address) {
			if (address.isPrimaryAddress()) {
				return address.getStreetAddress();
			}		
		}
		return "";		
	}
	
	public String getPrimaryCity() {
		for (Address address : address) {
			if (address.isPrimaryAddress()) {
				return address.getCity();
			}		
		}
		return "";		
	}
	
	
	public String getPrimaryCo() {
		for (Address address : address) {
			if (address.isPrimaryAddress()) {
				return address.getCo();
			}		
		}
		return "";		
	}
	
	public String getPrimaryCoForProfile() {
		for (Address address : address) {
			if (address.isPrimaryAddress() && address.getCo() != null) {			
				return address.getCoWithCOForProfile();
			}		
		}
		return "";		
	}
	
	
	//TODO better solution?
	public int getPrimaryZipCode() {
		for (Address address : address) {
			if (address.isPrimaryAddress()) {
				return address.getZipCode();
			}		
		}
		return -1;		
	}

	public String filterCompetence(String searchStr){
		String searchResult = "";
		for (PersonCompetence personCompetence : personCompetence) {
			if(personCompetence.getCompetence().getCompetenceName().toLowerCase().contains(searchStr.toLowerCase())) {
				searchResult += personCompetence.getCompetence().getCompetenceName() + ", ";
			}
		}
		
		return searchResult.substring(0, searchResult.length()-2);
	}
}