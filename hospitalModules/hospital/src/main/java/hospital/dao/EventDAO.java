package hospital.dao;

import hospital.model.Event;
import hospital.model.StatusEvent;

import java.math.BigInteger;
import java.util.List;

public interface EventDAO {
    void saveEvent(Event event);

    StatusEvent getStatusEventById(BigInteger id);

    List<Event> getAllEvents(BigInteger id);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    Event getEventById(BigInteger id);

    StatusEvent getStatusEventByTitle(String title);

    void update(Event event);

    List<Event> getAllEvents();

}
