package hospital.service;

import hospital.dto.event.EventAjax;
import hospital.dto.event.EventUIDto;
import hospital.model.Event;
import hospital.model.StatusEvent;

import java.util.List;

public interface EventService {
    List<Event> generateEvents(int id);

    List<EventUIDto> getAllEvents(int id);

    void deleteEvent(Event event);

    void updateDeleteEvent(Event event);

    List<EventAjax> getAllEventsForAjax(int id);

    List<EventAjax> eventsForStaff(String staffName);

    EventAjax changeStatus(String status, int id);

    Event getById(int id);

    StatusEvent getStatusEventByTitle(String title);

    void update(Event event);

}
