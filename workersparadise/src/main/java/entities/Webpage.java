package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the webpage database table.
 * 
 */
@Entity
@Table(name="webpage")
@NamedQuery(name="Webpage.findAll", query="SELECT w FROM Webpage w")
public class Webpage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WebpagePK id;

	private String link;

	@Column(name="webpage_description")
	private String webpageDescription;

	@Column(name="webpage_name")
	private String webpageName;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type type;

	public Webpage() {
	}

	public WebpagePK getId() {
		return this.id;
	}

	public void setId(WebpagePK id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getWebpageDescription() {
		return this.webpageDescription;
	}

	public void setWebpageDescription(String webpageDescription) {
		this.webpageDescription = webpageDescription;
	}

	public String getWebpageName() {
		return this.webpageName;
	}

	public void setWebpageName(String webpageName) {
		this.webpageName = webpageName;
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