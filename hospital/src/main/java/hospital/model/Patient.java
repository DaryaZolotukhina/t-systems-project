package hospital.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="patients", schema = "public")
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronymic")
    private String patronymic;
    @Column(name="diagnosis")
    private String diagnosis;

}
