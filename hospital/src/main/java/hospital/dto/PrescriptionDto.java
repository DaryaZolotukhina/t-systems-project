package hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hospital.model.Event;
import hospital.model.Medicine;
import hospital.model.Patient;
import hospital.model.Procedure;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

public class PrescriptionDto {
    private Integer id;
    private Patient patient;
    private Medicine medicine;
    private Procedure procedure;
    private Integer daySchedule;

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

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    private Integer weekSchedule;
    private Integer period;
    private Float dose;
    private boolean isDone;
    private List<Event> events;
    private boolean isDeleted;
}
