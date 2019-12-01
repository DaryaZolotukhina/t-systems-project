package hospital.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Diagnosises")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_diagnosis")
    private Integer id;
    @Column
    private String title;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnosis_type")
    private DiagnosisType diagnosisType;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public DiagnosisType getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(DiagnosisType diagnosisType) {
        this.diagnosisType = diagnosisType;
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

}
