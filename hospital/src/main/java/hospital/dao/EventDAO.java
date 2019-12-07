package hospital.dao;

import hospital.model.Event;
import hospital.model.StatusEvent;

import java.util.List;

public interface EventDAO {
    void saveEvent(Event event);

    StatusEvent getStatusEventById(int id);

    List<Event> getAllEvents(int id);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    Event getEventById(int id);

    StatusEvent getStatusEventByTitle(String title);

    void update(Event event);

    List<Event> getAllEvents();

}
