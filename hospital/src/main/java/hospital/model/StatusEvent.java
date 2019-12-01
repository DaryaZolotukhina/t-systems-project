package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="status_event")
public class StatusEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_statusEvent")
    private Integer id;
    @Column
    private String title;
    @OneToMany(mappedBy = "statusEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;
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
