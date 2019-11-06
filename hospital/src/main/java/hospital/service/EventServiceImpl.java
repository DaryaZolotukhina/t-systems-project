package hospital.service;

import hospital.dao.EventDAO;
import hospital.dao.PatientDAO;
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

    private PrescriptionService prescriptionService;

    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public List<Event> getAllEvents(int id){
        List<Event> events=this.eventDAO.getAllEvents(id);
        return events;
    }

    @Override
    @Transactional
    public List<Event> generateEvents(int id) {
        Prescription presc=this.prescriptionService.getPrescriptionById(id);
        List<Event> events=new ArrayList<Event>();
        int period=presc.getPeriod();
        int eventCnt;
        List<Date> dates;
        int day = org.springframework.util.StringUtils.countOccurrencesOf(presc.getDaySchedule(), "1");
        if (day>0){
            eventCnt=period*day;
            dates=calcDateTime(period,presc.getDaySchedule());
        }
        else{
            int week = org.springframework.util.StringUtils.countOccurrencesOf(presc.getWeekSchedule(), "1");
            eventCnt=period*week;
            dates=calcDate(period,presc.getWeekSchedule());
        }
        for(int i=0;i<eventCnt;i++){
            Event event=new Event();
            event.setPatient(presc.getPatient());
            event.setProcMed(presc.getProcMed());
            event.setPrescription(presc);
            event.setDateTimeEvent(dates.get(i));
            event.setStatusEvent(this.eventDAO.getStatusEventById(1));
            this.eventDAO.saveEvent(event);
            events.add(event);
        }
        presc.setIsDone(true);
        this.prescriptionService.updatePrescription(presc);
        return events;
    }
}
