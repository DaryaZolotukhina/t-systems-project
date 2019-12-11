package hospital.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreatePatientRequest {
    @NotBlank(message = "Surname is mandatory")
    @Pattern(regexp = "^[A-z]+$")
    private String surname;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-z]+$")
    private String name;
    @NotBlank(message = "Patronymic is mandatory")
    @Pattern(regexp = "^[A-z]+$")
    private String patronymic;
    @NotBlank(message = "Insurance number is mandatory")
    @Pattern(regexp="^[0-9]+$")
    private String insuranceNum;
    @NotNull(message= "Doctor is mandatory")
    private int staffId;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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



}
