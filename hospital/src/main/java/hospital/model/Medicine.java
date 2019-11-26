package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_medicine")
    private Integer id;
    @Column
    private String title;
    @JsonIgnore
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;
    @JsonIgnore
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> events;
    @ManyToMany(mappedBy="medicines")
    private List<DiagnosisType> diagnosisTypes;

    public List<DiagnosisType> getDiagnosisTypes() {
        return diagnosisTypes;
    }

    public void setDiagnosisTypes(List<DiagnosisType> diagnosisTypes) {
        this.diagnosisTypes = diagnosisTypes;
    }

    public List<Prescription> getEvents() {
        return events;
    }

    public void setEvents(List<Prescription> events) {
        this.events = events;
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
