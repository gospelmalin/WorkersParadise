package com.yhsipi.workersparadise.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the education database table.
 * 
 */
@Entity
@Table(name="education")
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EducationPK id;

	@Column(name="education_description")
	private String educationDescription;

	@Temporal(TemporalType.DATE)
	@Column(name="education_end_date")
	private Date educationEndDate;

	@Column(name="education_end_day")
	private byte educationEndDay;

	@Column(name="education_end_month")
	private byte educationEndMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="education_end_year")
	private Date educationEndYear;

	@Column(name="education_grade")
	private String educationGrade;

	@Column(name="education_level")
	private String educationLevel;

	@Column(name="education_name")
	private String educationName;

	@Temporal(TemporalType.DATE)
	@Column(name="education_start_date")
	private Date educationStartDate;

	@Column(name="education_start_day")
	private byte educationStartDay;

	@Column(name="education_start_month")
	private byte educationStartMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="education_start_year")
	private Date educationStartYear;

	@Column(name="is_program")
	private boolean isProgram;

	private String school;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	
	public Education() {
	}
	
	public EducationPK getId() {
		return this.id;
	}

	public void setId(EducationPK id) {
		this.id = id;
	}
	

	public String getEducationDescription() {
		return this.educationDescription;
	}

	public void setEducationDescription(String educationDescription) {
		this.educationDescription = educationDescription;
	}

	public Date getEducationEndDate() {
		return this.educationEndDate;
	}

	public void setEducationEndDate(Date educationEndDate) {
		this.educationEndDate = educationEndDate;
	}

	public byte getEducationEndDay() {
		return this.educationEndDay;
	}

	public void setEducationEndDay(byte educationEndDay) {
		this.educationEndDay = educationEndDay;
	}

	public byte getEducationEndMonth() {
		return this.educationEndMonth;
	}

	public void setEducationEndMonth(byte educationEndMonth) {
		this.educationEndMonth = educationEndMonth;
	}

	public Date getEducationEndYear() {
		return this.educationEndYear;
	}

	public void setEducationEndYear(Date educationEndYear) {
		this.educationEndYear = educationEndYear;
	}

	public String getEducationGrade() {
		return this.educationGrade;
	}

	public void setEducationGrade(String educationGrade) {
		this.educationGrade = educationGrade;
	}

	public String getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getEducationName() {
		return this.educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public Date getEducationStartDate() {
		return this.educationStartDate;
	}

	public void setEducationStartDate(Date educationStartDate) {
		this.educationStartDate = educationStartDate;
	}

	public byte getEducationStartDay() {
		return this.educationStartDay;
	}

	public void setEducationStartDay(byte educationStartDay) {
		this.educationStartDay = educationStartDay;
	}

	public byte getEducationStartMonth() {
		return this.educationStartMonth;
	}

	public void setEducationStartMonth(byte educationStartMonth) {
		this.educationStartMonth = educationStartMonth;
	}

	public Date getEducationStartYear() {
		return this.educationStartYear;
	}

	public void setEducationStartYear(Date educationStartYear) {
		this.educationStartYear = educationStartYear;
	}

	public boolean getIsProgram() {
		return this.isProgram;
	}

	public void setIsProgram(boolean isProgram) {
		this.isProgram = isProgram;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", educationDescription=" + educationDescription + ", educationEndDate="
				+ educationEndDate + ", educationEndDay=" + educationEndDay + ", educationEndMonth=" + educationEndMonth
				+ ", educationEndYear=" + educationEndYear + ", educationGrade=" + educationGrade + ", educationLevel="
				+ educationLevel + ", educationName=" + educationName + ", educationStartDate=" + educationStartDate
				+ ", educationStartDay=" + educationStartDay + ", educationStartMonth=" + educationStartMonth
				+ ", educationStartYear=" + educationStartYear + ", isProgram=" + isProgram + ", school=" + school
				+ ", person=" + person + "]";
	}

	

}