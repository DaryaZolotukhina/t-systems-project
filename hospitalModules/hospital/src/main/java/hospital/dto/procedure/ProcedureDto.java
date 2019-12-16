package hospital.dto.procedure;

import hospital.model.Prescription;

import java.math.BigInteger;
import java.util.List;

public class ProcedureDto {
    private BigInteger id;
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
