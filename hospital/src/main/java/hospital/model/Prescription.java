package hospital.model;

import javax.persistence.*;

@Entity
@Table(name="Prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prescription")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_procMed")
    private ProcMed procMed;
    @Column
    private int[] daySchedule;
    @Column
    private int[] weekSchedule;
    @Column
    private Integer period;
    @Column
    private Float dose;
    @Column
    private boolean isDone;

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

    public int[] getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(int[] daySchedule) {
        this.daySchedule = daySchedule;
    }

    public int[] getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(int[] weekSchedule) {
        this.weekSchedule = weekSchedule;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
