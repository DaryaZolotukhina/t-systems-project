package hospital.service;

import hospital.model.Event;

import java.util.List;

public interface EventService {
    List<Event> generateEvents(int id);

    List<Event> getAllEvents(int id);

    void deleteEvent(Event event);

    void updateDeleteEvent(Event event);

}
