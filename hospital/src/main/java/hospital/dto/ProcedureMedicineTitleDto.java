package hospital.dto;

import hospital.model.Prescription;
import hospital.model.TypeProcedureMedicine;

import java.util.List;

public class ProcedureMedicineTitleDto {
    private Integer id;
    private String title;
    private TypeProcedureMedicine typeProcedureMedicine;
    private List<Prescription> prescriptions;
    private List<Prescription> events;

    public TypeProcedureMedicine getTypeProcedureMedicine() {
        return typeProcedureMedicine;
    }

    public void setTypeProcedureMedicine(TypeProcedureMedicine typeProcedureMedicine) {
        this.typeProcedureMedicine = typeProcedureMedicine;
    }

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
