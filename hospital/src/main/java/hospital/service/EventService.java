package hospital.service;

import hospital.dto.EventAjax;
import hospital.dto.EventDto;
import hospital.dto.EventUIDto;
import hospital.model.Event;
import hospital.model.StatusEvent;

import java.util.List;

public interface EventService {
    List<Event> generateEvents(int id);

    List<EventUIDto> getAllEvents(int id);

    void deleteEvent(Event event);

    void updateDeleteEvent(Event event);

    List<EventAjax> getAllEventsForAjax(int id);

    List<EventDto> eventsForStaff(int id);

    void changeStatus(String status, int id);

    Event getById(int id);

    StatusEvent getStatusEventByTitle(String title);

    void update(Event event);

}
