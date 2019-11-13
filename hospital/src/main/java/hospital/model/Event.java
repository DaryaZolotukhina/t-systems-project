package hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_event")
    private Integer id;
    @JsonIgnore
    @Transient
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @Column
    private Date dateTimeEvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcMed getProcMed() {
        return procMed;
    }

    public void setProcMed(ProcMed procMed) {
        this.procMed = procMed;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_procMed")
    private ProcMed procMed;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prescription")
    private Prescription prescription;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_statusEvent")
    private StatusEvent statusEvent;
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column
    private boolean isDeleted;
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(Date dateTimeEvent) {
        this.dateTimeEvent = dateTimeEvent;
    }

    public StatusEvent getStatusEvent() {
        return statusEvent;
    }

    public void setStatusEvent(StatusEvent statusEvent) {
        this.statusEvent = statusEvent;
    }


    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
