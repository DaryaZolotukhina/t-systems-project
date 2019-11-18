package hospital.controller;

import hospital.model.Event;
import hospital.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService eventService) {
        this.eventService= eventService;
    }

    @RequestMapping(value = "/prescription/{idPat}/{idPresc}", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> generateEvents(@PathVariable("idPat") int idPat, @PathVariable("idPresc") int idPresc){
        eventService.generateEvents(idPresc);
        return eventService.getAllEvents(idPat);
    }
}
