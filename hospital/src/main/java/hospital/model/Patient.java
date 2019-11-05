package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_patient")
	private Integer id;
	@Column(name="surname")
	private String surname;
	@Column(name="name")
	private String name;
	@Column(name="patronymic")
	private String patronymic;
	@Column
	private String insuranceNum;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_doctor")
	private Doctor doctor;
	@Column
	private boolean isDeleted;
	@Column
	private boolean isDischarged;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Patient_Diagnosis> patDiag;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private List<Prescription> prescriptions;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private List<Event> events;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public boolean isDischarged() {
		return isDischarged;
	}

	public void setDischarged(boolean discharged) {
		isDischarged = discharged;
	}

    public List<Patient_Diagnosis> getPatDiag() {
        return patDiag;
    }

    public void setPatDiag(List<Patient_Diagnosis> patDiag) {
        this.patDiag = patDiag;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
