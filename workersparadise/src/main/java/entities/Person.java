package entities;

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

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="person")
	private List<Address> addresses;

	//bi-directional many-to-one association to Certification
	@OneToMany(mappedBy="person")
	private List<Certification> certifications;

	//bi-directional many-to-one association to Competence
	@OneToMany(mappedBy="person")
	private List<Competence> competences;

	//bi-directional many-to-one association to Education
	@OneToMany(mappedBy="person")
	private List<Education> educations;

	//bi-directional many-to-one association to Email
	@OneToMany(mappedBy="person")
	private List<Email> emails;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="person")
	private List<Phone> phones;

	//bi-directional many-to-one association to ProfessionalExperience
	@OneToMany(mappedBy="person")
	private List<ProfessionalExperience> professionalExperiences;

	//bi-directional many-to-one association to Reference
	@OneToMany(mappedBy="person1")
	private List<Reference> references1;

	//bi-directional many-to-one association to Reference
	@OneToMany(mappedBy="person2")
	private List<Reference> references2;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="person")
	private List<User> users;

	//bi-directional many-to-one association to Webpage
	@OneToMany(mappedBy="person")
	private List<Webpage> webpages;

	public Person() {
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

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setPerson(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setPerson(null);

		return address;
	}

	public List<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public Certification addCertification(Certification certification) {
		getCertifications().add(certification);
		certification.setPerson(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setPerson(null);

		return certification;
	}

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public Competence addCompetence(Competence competence) {
		getCompetences().add(competence);
		competence.setPerson(this);

		return competence;
	}

	public Competence removeCompetence(Competence competence) {
		getCompetences().remove(competence);
		competence.setPerson(null);

		return competence;
	}

	public List<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public Education addEducation(Education education) {
		getEducations().add(education);
		education.setPerson(this);

		return education;
	}

	public Education removeEducation(Education education) {
		getEducations().remove(education);
		education.setPerson(null);

		return education;
	}

	public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Email addEmail(Email email) {
		getEmails().add(email);
		email.setPerson(this);

		return email;
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		email.setPerson(null);

		return email;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setPerson(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setPerson(null);

		return phone;
	}

	public List<ProfessionalExperience> getProfessionalExperiences() {
		return this.professionalExperiences;
	}

	public void setProfessionalExperiences(List<ProfessionalExperience> professionalExperiences) {
		this.professionalExperiences = professionalExperiences;
	}

	public ProfessionalExperience addProfessionalExperience(ProfessionalExperience professionalExperience) {
		getProfessionalExperiences().add(professionalExperience);
		professionalExperience.setPerson(this);

		return professionalExperience;
	}

	public ProfessionalExperience removeProfessionalExperience(ProfessionalExperience professionalExperience) {
		getProfessionalExperiences().remove(professionalExperience);
		professionalExperience.setPerson(null);

		return professionalExperience;
	}

	public List<Reference> getReferences1() {
		return this.references1;
	}

	public void setReferences1(List<Reference> references1) {
		this.references1 = references1;
	}

	public Reference addReferences1(Reference references1) {
		getReferences1().add(references1);
		references1.setPerson1(this);

		return references1;
	}

	public Reference removeReferences1(Reference references1) {
		getReferences1().remove(references1);
		references1.setPerson1(null);

		return references1;
	}

	public List<Reference> getReferences2() {
		return this.references2;
	}

	public void setReferences2(List<Reference> references2) {
		this.references2 = references2;
	}

	public Reference addReferences2(Reference references2) {
		getReferences2().add(references2);
		references2.setPerson2(this);

		return references2;
	}

	public Reference removeReferences2(Reference references2) {
		getReferences2().remove(references2);
		references2.setPerson2(null);

		return references2;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setPerson(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setPerson(null);

		return user;
	}

	public List<Webpage> getWebpages() {
		return this.webpages;
	}

	public void setWebpages(List<Webpage> webpages) {
		this.webpages = webpages;
	}

	public Webpage addWebpage(Webpage webpage) {
		getWebpages().add(webpage);
		webpage.setPerson(this);

		return webpage;
	}

	public Webpage removeWebpage(Webpage webpage) {
		getWebpages().remove(webpage);
		webpage.setPerson(null);

		return webpage;
	}

}