package hospital.service.serviceImpls;

import hospital.dao.EventDAO;
import hospital.dao.PrescriptionDAO;
import hospital.dto.EventDto;
import hospital.dto.mappers.EventMapper;
import hospital.dto.mappers.PrescriptionMapper;
import hospital.model.Event;
import hospital.model.Prescription;
import hospital.service.EventService;
import hospital.service.PrescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private PrescriptionDAO prescriptionDAO;
    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<EventDto> getAllEvents(int id){
        List<EventDto> listEventDto=new ArrayList<>();
        List<Event> listEvent= eventDAO.getAllEvents(id);
        for (Event event : listEvent){
            listEventDto.add(EventMapper.EVENT_MAPPER.fromEvent(event));
        }
        return listEventDto;
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
            eventDto.setProcedureMedicine(presc.getProcedureMedicine());
            eventDto.setPrescription(presc);
            eventDto.setDateTimeEvent(dates.get(i));
            eventDto.setStatusEvent(eventDAO.getStatusEventById(1));
            eventsDto.add(eventDto);
        }
        List<Event> listEvent= new ArrayList<>();
        for (EventDto eventDto : eventsDto){
            Event event=EventMapper.EVENT_MAPPER.toEvent(eventDto);
            listEvent.add(event);
            eventDAO.saveEvent(event);
        }
        presc.setIsDone(true);
        prescriptionDAO.updatePrescription(presc);
        return listEvent;
    }

}
