package hospital.dto;

import hospital.model.Prescription;

import java.util.List;

public class MedicineDto {
    private Integer id;
    private String title;
    private List<Prescription> prescriptions;
    private List<Prescription> events;

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getEvents() {
        return events;
    }

    public void setEvents(List<Prescription> events) {
        this.events = events;
    }

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
