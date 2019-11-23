package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prescription")
    private Integer id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_medicine")
    private Medicine medicine;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_procedure")
    private Procedure procedure;
    @Column
    private Integer daySchedule;
    @Column
    private Integer weekSchedule;
    @Column
    private Integer period;
    @Column
    private Float dose;
    @Column
    private boolean isDone;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Event> events;
    @Column
    private boolean isDeleted;

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(Integer daySchedule) {
        this.daySchedule = daySchedule;
    }

    public Integer getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(Integer weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Float getDose() {
        return dose;
    }

    public void setDose(Float dose) {
        this.dose = dose;
    }


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
