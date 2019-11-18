package hospital.service;

import hospital.dao.EventDAO;
import hospital.dao.PrescriptionDAO;
import hospital.model.Event;
import hospital.model.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hospital.utils.Utils.calcDate;
import static hospital.utils.Utils.calcDateTime;

@Service
public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;
    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    private PrescriptionService prescriptionService;
    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService= prescriptionService;
    }

    private PrescriptionDAO prescriptionDAO;
    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<Event> getAllEvents(int id){
        List<Event> events=eventDAO.getAllEvents(id);
        return events;
    }

    @Override
    @Transactional
    public void deleteEvent(Event event){
        eventDAO.deleteEvent(event);
    }

    @Override
    @Transactional
    public void updateDeleteEvent(Event event) {
        event.setIsDeleted(true);
        eventDAO.updateEvent(event);
    }

    @Override
    @Transactional
    public List<Event> generateEvents(int id) {
        Prescription presc=prescriptionService.getPrescriptionById(id);
        List<Event> events=new ArrayList<Event>();
        int period=presc.getPeriod();
        int eventCnt;
        List<Date> dates;
        int day = presc.getDaySchedule();
        if (day>0){
            dates=calcDateTime(period,presc.getDaySchedule());
            eventCnt=dates.size();
        }
        else{
            dates=calcDate(period,presc.getWeekSchedule());
            eventCnt=dates.size();
        }
        for(int i=0;i<eventCnt;i++){
            Event event=new Event();
            event.setPatient(presc.getPatient());
            event.setProcMed(presc.getProcMed());
            event.setPrescription(presc);
            event.setDateTimeEvent(dates.get(i));
            event.setStatusEvent(eventDAO.getStatusEventById(1));
            eventDAO.saveEvent(event);
            events.add(event);
        }
        presc.setIsDone(true);
        prescriptionDAO.updatePrescription(presc);
        return events;
    }

}
