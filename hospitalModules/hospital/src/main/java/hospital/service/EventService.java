package hospital.service;

import hospital.dto.event.EventAjax;
import hospital.dto.event.EventUIDto;
import hospital.model.Event;
import hospital.model.StatusEvent;

import java.math.BigInteger;
import java.util.List;

public interface EventService {
    List<Event> generateEvents(BigInteger id);

    List<EventUIDto> getAllEvents(BigInteger id);

    void deleteEvent(Event event);

    void updateDeleteEvent(Event event);

    List<EventAjax> getAllEventsForAjax(BigInteger id);

    List<EventAjax> eventsForStaff(String staffName);

    EventAjax changeStatus(String status, BigInteger id);

    Event getById(BigInteger id);

    StatusEvent getStatusEventByTitle(String title);

    void update(Event event);

}
