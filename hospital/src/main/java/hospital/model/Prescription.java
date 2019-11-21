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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_procMed")
    private ProcedureMedicine procedureMedicine;
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
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Event> events;
    @Column
    private boolean isDeleted;

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

    public ProcedureMedicine getProcedureMedicine() {
        return procedureMedicine;
    }

    public void setProcedureMedicine(ProcedureMedicine procedureMedicine) {
        this.procedureMedicine = procedureMedicine;
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
