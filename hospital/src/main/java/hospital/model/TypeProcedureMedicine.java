package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="typeProcMed")
public class TypeProcedureMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_type")
    private Integer id;
    @Column
    private String title;

    @OneToMany(mappedBy = "typeProcedureMedicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureMedicine> procedureMedicineList;
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
