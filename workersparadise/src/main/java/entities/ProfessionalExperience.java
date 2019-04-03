package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the professional_experience database table.
 * 
 */
@Entity
@Table(name="professional_experience")
@NamedQuery(name="ProfessionalExperience.findAll", query="SELECT p FROM ProfessionalExperience p")
public class ProfessionalExperience implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProfessionalExperiencePK id;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="end_day")
	private int endDay;

	@Column(name="end_month")
	private int endMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="end_year")
	private Date endYear;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="start_day")
	private int startDay;

	@Column(name="start_month")
	private int startMonth;

	@Temporal(TemporalType.DATE)
	@Column(name="start_year")
	private Date startYear;

	private String title;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="id_company")
	private Company company;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person",insertable=false, updatable=false)
	private Person person;

	public ProfessionalExperience() {
	}

	public ProfessionalExperiencePK getId() {
		return this.id;
	}

	public void setId(ProfessionalExperiencePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getEndDay() {
		return this.endDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	public int getEndMonth() {
		return this.endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public Date getEndYear() {
		return this.endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStartDay() {
		return this.startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public int getStartMonth() {
		return this.startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public Date getStartYear() {
		return this.startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}