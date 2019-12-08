package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_specialization")
    private Integer id;
    @Column
    private String title;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="Specialization_procedures",
            joinColumns=@JoinColumn(name="id_specialization", referencedColumnName="id_specialization"),
            inverseJoinColumns=@JoinColumn(name="id_procedure", referencedColumnName="id_procedure"))
    private List<Procedure> procedures;
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Staff> staff;

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
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

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
