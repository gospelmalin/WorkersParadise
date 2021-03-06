package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name="users")
@NamedQuery(name="user.findAll", query="SELECT u FROM Users u")

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_user")
    private int idUser;

    @Override
	public String toString() {
		return "Users [idUser=" + idUser + ", dateCreated=" + dateCreated + ", enabled=" + enabled + ", password="
				+ password + ", url=" + url + ", username=" + username + ", person=" + person + "]";
	}

	@Column(name="date_created")
    private Timestamp dateCreated;

    private String enabled;

    private String password;

    private String url;

    private String username;

    //bi-directional many-to-one association to Person
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_person")
    public Person person;

    public Users() {
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Timestamp getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}