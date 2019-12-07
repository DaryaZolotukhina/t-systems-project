package hospital.dto;

import hospital.model.Diagnosis;
import hospital.model.Medicine;
import hospital.model.Procedure;

import java.util.List;

public class DiagnosisTypeDto {
    private Integer id;
    private String title;
    private List<Diagnosis> diagnosises;
    private List<Procedure> procedures;
    private List<Medicine> medicines;
    public List<Procedure> getProcedures() {
        return procedures;
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

    public List<Diagnosis> getDiagnosises() {
        return diagnosises;
    }

    public void setDiagnosises(List<Diagnosis> diagnosises) {
        this.diagnosises = diagnosises;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
