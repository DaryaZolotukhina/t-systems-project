package hospital.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hospital.model.*;

import javax.persistence.*;
import java.util.Date;

public class EventDto {
    private Integer id;
    private Patient patient;
    private Date dateTimeEvent;
    private Medicine medicine;
    private Procedure procedure;
    private Prescription prescription;
    private StatusEvent statusEvent;
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
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

    public Date getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(Date dateTimeEvent) {
        this.dateTimeEvent = dateTimeEvent;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public StatusEvent getStatusEvent() {
        return statusEvent;
    }

    public void setStatusEvent(StatusEvent statusEvent) {
        this.statusEvent = statusEvent;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    private boolean isDeleted;
}
