package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_staff")
    private Integer id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronymic")
    private String patronymic;
    @Column(name="isdeleted")
    private boolean isDeleted;
    @Column
    private boolean isDoctor;

    public boolean getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "staff", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Patient> patients;

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

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
