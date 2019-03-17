package com.yhsipi.workersparadise.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer id;

	@NotNull
	@Size(min=2, max=255, message="Min=2 max=255 bokstäver")
	@Column(length=255, nullable=false)
	private String fName;

	@NotNull
	@Size(min=2, max=255, message="Min=2 max=255 bokstäver")
	@Column(length=255, nullable=false)
	private String lName;


	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}

}