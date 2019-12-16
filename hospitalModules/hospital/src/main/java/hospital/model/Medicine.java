package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="Medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_medicine")
    private BigInteger id;
    @Column
    private String title;
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Prescription> prescriptions;
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Prescription> events;
    @ManyToMany(mappedBy="medicines",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
