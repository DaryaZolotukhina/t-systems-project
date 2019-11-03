package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="procMed")
public class ProcMed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_procMed")
    private Integer id;
    @Column
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_statusPat")
    private TypeProcMed typeProcMed;

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

    public TypeProcMed getTypeProcMed() {
        return typeProcMed;
    }

    public void setTypeProcMed(TypeProcMed typeProcMed) {
        this.typeProcMed = typeProcMed;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @OneToMany(mappedBy = "procMed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;
}
