package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="diagnosis_type")
public class DiagnosisType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_diagnosis_type")
    private BigInteger id;
    @Column
    private String title;
    @OneToMany(mappedBy = "diagnosisType", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Diagnosis> diagnosises;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="diagnosis_type_procedures",
            joinColumns=@JoinColumn(name="id_diagnosis_type", referencedColumnName="id_diagnosis_type"),
            inverseJoinColumns=@JoinColumn(name="id_procedure", referencedColumnName="id_procedure"))
    private List<Procedure> procedures;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="diagnosis_type_medicines",
            joinColumns=@JoinColumn(name="id_diagnosis_type", referencedColumnName="id_diagnosis_type"),
            inverseJoinColumns=@JoinColumn(name="id_medicine", referencedColumnName="id_medicine"))
    private List<Medicine> medicines;

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<Diagnosis> getDiagnosises() {
        return diagnosises;
    }

    public void setDiagnosises(List<Diagnosis> diagnosises) {
        this.diagnosises = diagnosises;
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
}
