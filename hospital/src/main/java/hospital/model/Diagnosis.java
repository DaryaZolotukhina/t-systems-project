package hospital.model;

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
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnosis_type")
    private DiagnosisType diagnosisType;

    @ManyToMany(mappedBy="diagnosises")
    private List<Patient> patients;


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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
