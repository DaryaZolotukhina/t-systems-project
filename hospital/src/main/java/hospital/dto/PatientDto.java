package hospital.dto;

import hospital.model.PatientDiagnosis;
import hospital.model.Prescription;
import hospital.model.Staff;

import java.util.List;

public class PatientDto {
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String insuranceNum;
    private Staff staff;
    private boolean isDeleted;
    private boolean isDischarged;
    private List<PatientDiagnosis> patDiag;
    private List<Prescription> prescriptions;

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public boolean getIsDischarged() {
        return isDischarged;
    }

    public void setIsDischarged(boolean discharged) {
        isDischarged = discharged;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<PatientDiagnosis> getPatDiag() {
        return patDiag;
    }

    public void setPatDiag(List<PatientDiagnosis> patDiag) {
        this.patDiag = patDiag;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

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


}