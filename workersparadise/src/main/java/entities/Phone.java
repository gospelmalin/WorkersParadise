package entities;

import java.io.Serializable;
import javax.persistence.*;


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
	private byte primaryContactNumber;

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

	public byte getPrimaryContactNumber() {
		return this.primaryContactNumber;
	}

	public void setPrimaryContactNumber(byte primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
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

}