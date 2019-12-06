package hospital.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="staff_type")
public class StaffType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_staff_type")
    private Integer id;
    @Column
    private String title;
    @OneToMany(mappedBy = "staffType", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Staff> staff;

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

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
}
