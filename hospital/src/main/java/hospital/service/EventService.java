package hospital.service;

import hospital.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(int id);

    List<Event> generateEvents(int id);
}
