package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="status_event")
public class StatusEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_statusEvent")
    private BigInteger id;
    @Column
    private String title;
    @OneToMany(mappedBy = "statusEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
