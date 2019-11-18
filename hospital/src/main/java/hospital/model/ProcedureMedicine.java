package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="procMed")
public class ProcedureMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_procMed")
    private Integer id;
    @Column
    private String title;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type")
    private TypeProcedureMedicine typeProcedureMedicine;

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

    public TypeProcedureMedicine getTypeProcedureMedicine() {
        return typeProcedureMedicine;
    }

    public void setTypeProcedureMedicine(TypeProcedureMedicine typeProcedureMedicine) {
        this.typeProcedureMedicine = typeProcedureMedicine;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "procedureMedicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;
    @JsonIgnore
    @OneToMany(mappedBy = "procedureMedicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> events;
}
