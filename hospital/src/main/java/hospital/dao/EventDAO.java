package hospital.dao;

import hospital.model.Event;
import hospital.model.StatusEvent;

import java.util.List;

public interface EventDAO {

    StatusEvent getStatusEventById(int id);

    List<Event> getAllEvents(int id);

    void saveEvent(Event event);
}
