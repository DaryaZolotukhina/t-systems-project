package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_procedure")
    private Integer id;
    @Column
    private String title;
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Prescription> prescriptions;
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Prescription> events;
    @ManyToMany(mappedBy="procedures",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<DiagnosisType> diagnosisTypes;
    @ManyToMany(mappedBy="procedures",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Specialization> specializations;

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public List<Prescription> getEvents() {
        return events;
    }

    public void setEvents(List<Prescription> events) {
        this.events = events;
    }

    public List<DiagnosisType> getDiagnosisTypes() {
        return diagnosisTypes;
    }

    public void setDiagnosisTypes(List<DiagnosisType> diagnosisTypes) {
        this.diagnosisTypes = diagnosisTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}