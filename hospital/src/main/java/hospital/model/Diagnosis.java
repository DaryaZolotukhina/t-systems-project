package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Diagnosises")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Diagnosis")
    private Integer id;
    @Column
    private String title;

    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient_Diagnosis> patDiag;

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
