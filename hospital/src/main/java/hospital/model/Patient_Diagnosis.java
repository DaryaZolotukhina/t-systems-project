package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="patient_diagnosis")
public class Patient_Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_patient_diagnosis")
    private Integer id;
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnosis")
    private Diagnosis diagnosis;
}
