package hospital.dao;

import hospital.model.Event;
import hospital.model.StatusEvent;

import java.util.List;

public interface EventDAO {
    public void saveEvent(Event event);

    StatusEvent getStatusEventById(int id);

    List<Event> getAllEvents(int id);

    void deleteEvent(Event event);

    void updateEvent(Event event);

}
