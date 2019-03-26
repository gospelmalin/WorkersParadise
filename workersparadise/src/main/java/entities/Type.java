package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@Table(name="type")
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type")
	private int idType;

	@Column(name="type_name")
	private String typeName;

	@Column(name="type_no")
	private int typeNo;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="type")
	private List<Address> addresses;

	//bi-directional many-to-one association to Email
	@OneToMany(mappedBy="type")
	private List<Email> emails;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="type")
	private List<Phone> phones;

	//bi-directional many-to-one association to Webpage
	@OneToMany(mappedBy="type")
	private List<Webpage> webpages;

	public Type() {
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setType(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setType(null);

		return address;
	}

	public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Email addEmail(Email email) {
		getEmails().add(email);
		email.setType(this);

		return email;
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		email.setType(null);

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
		phone.setType(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setType(null);

		return phone;
	}

	public List<Webpage> getWebpages() {
		return this.webpages;
	}

	public void setWebpages(List<Webpage> webpages) {
		this.webpages = webpages;
	}

	public Webpage addWebpage(Webpage webpage) {
		getWebpages().add(webpage);
		webpage.setType(this);

		return webpage;
	}

	public Webpage removeWebpage(Webpage webpage) {
		getWebpages().remove(webpage);
		webpage.setType(null);

		return webpage;
	}

}