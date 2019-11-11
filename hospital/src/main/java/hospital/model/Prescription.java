package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prescription")
    private Integer id;

    public Integer getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(Integer daySchedule) {
        this.daySchedule = daySchedule;
    }

    public String getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(String weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_procMed")
    private ProcMed procMed;
    @Column
    private Integer daySchedule;
    @Column
    private String weekSchedule;
    @Column
    private Integer period;
    @Column
    private Float dose;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column
    private boolean isDeleted;

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    @Column
    private boolean isDone;
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Event> events;

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

    public ProcMed getProcMed() {
        return procMed;
    }

    public void setProcMed(ProcMed procMed) {
        this.procMed = procMed;
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
