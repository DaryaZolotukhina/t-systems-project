package hospital.service.serviceImpl;

import hospital.dao.EventDAO;
import hospital.dao.PrescriptionDAO;
import hospital.dto.*;
import hospital.mappers.EventMapper;
import hospital.mappers.StaffMapper;
import hospital.model.*;
import hospital.service.EventService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static hospital.service.utils.CalculationDateUtils.calcDate;
import static hospital.service.utils.CalculationDateUtils.calcDateTime;

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

    private PatientService patientService;
    public void setPatientService(PatientService patientService) {
        this.patientService= patientService;
    }

    private PrescriptionDAO prescriptionDAO;
    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<EventUIDto> getAllEvents(int id){
        List<EventUIDto> listEventUIDto=new ArrayList<>();
        List<Event> listEvent= eventDAO.getAllEvents(id);
        for (Event event : listEvent){
            listEventUIDto.add(EventMapper.EVENT_MAPPER.fromEventUI(event));
        }
        return listEventUIDto;
    }

    @Override
    @Transactional
    public List<EventAjax> getAllEventsForAjax(int id){
        List<EventAjax> listEventAjax=new ArrayList<>();
        List<Event> listEvent= eventDAO.getAllEvents(id);
        for (Event event : listEvent){
            listEventAjax.add(EventMapper.EVENT_MAPPER.fromEventAjax(event));
        }
        return listEventAjax;
    }

    @Override
    @Transactional
    public List<EventAjax> eventsForStaff(int id){
        List<Event> listEvent= eventDAO.getAllEvents();
        List<EventAjax> listEventAjax=new ArrayList<>();
        Calendar date = Calendar.getInstance();

        for (Event event : listEvent){
            Calendar c = new GregorianCalendar();
            c.setTime(event.getDateTimeEvent());

            if ((event.getStaff().getId()==id) && (c.get(Calendar.DAY_OF_MONTH)==(date.get(Calendar.DAY_OF_MONTH)+1))
                    && (c.get(Calendar.MONTH)==date.get(Calendar.MONTH))
                    && (c.get(Calendar.YEAR)==(date.get(Calendar.YEAR)))) {
                listEventAjax.add(EventMapper.EVENT_MAPPER.fromEventAjax(event));
            }
        }
        return listEventAjax;
    }

    @Override
    @Transactional
    public Event getById(int id) {
        return eventDAO.getEventById(id);
    }

    @Override
    @Transactional
    public StatusEvent getStatusEventByTitle(String title) {
        StatusEvent statusEvent=eventDAO.getStatusEventByTitle(title);
        return statusEvent;
    }

    @Override
    @Transactional
    public void update(Event event){
        eventDAO.updateEvent(event);
    }

    /*@Override
    @Transactional
    public void changeStatus(String status, int id){
        Event event=getById(id);
        StatusEvent statusEvent=getStatusEventByTitle(status);
        event.setStatusEvent(statusEvent);
        update(event);
    }*/

    @Override
    @Transactional
    public EventAjax changeStatus(String status, int id){
        Event event=getById(id);
        StatusEvent statusEvent=getStatusEventByTitle(status);
        event.setStatusEvent(statusEvent);
        update(event);
        return EventMapper.EVENT_MAPPER.fromEventAjax(event);
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
        Prescription presc = prescriptionDAO.getPrescriptionById(id);
        List<EventDto> eventsDto=new ArrayList<>();
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
            EventDto eventDto=new EventDto();
            eventDto.setPatient(presc.getPatient());
            if (presc.getProcedure()!=null) {
                eventDto.setProcedure(presc.getProcedure());
            }
            else {
                eventDto.setMedicine(presc.getMedicine());
            }
            eventDto.setPrescription(presc);
            eventDto.setDateTimeEvent(dates.get(i));
            eventDto.setStatusEvent(eventDAO.getStatusEventById(1));
            eventsDto.add(eventDto);
        }
        List<Event> listEvent= new ArrayList<>();
        for (EventDto eventDto : eventsDto){
            Event event=EventMapper.EVENT_MAPPER.toEvent(eventDto);
            event.setStaff(StaffMapper.STAFF_MAPPER.toStaff(patientService.getDoctorBySurname("Semenov")));
            listEvent.add(event);
            eventDAO.saveEvent(event);
        }
        presc.setIsDone(true);
        prescriptionDAO.updatePrescription(presc);
        return listEvent;
    }

}