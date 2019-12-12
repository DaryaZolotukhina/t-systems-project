package hospital.controller;

import hospital.dto.event.EventAjax;
import hospital.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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
    List<EventAjax> generateEvents(@PathVariable("idPat") int idPat, @PathVariable("idPresc") int idPresc){
        eventService.generateEvents(idPresc);
        return eventService.getAllEventsForAjax(idPat);
    }

    @RequestMapping(value = "/events/{staffName}", method = RequestMethod.GET)
    public @ResponseBody List<EventAjax> eventsForStaff(@PathVariable("staffName") String staffName){
       return eventService.eventsForStaff(staffName);
    }

    @RequestMapping(value = "/changeStatus/{idEvent}/{idStaff}/{status}", method = RequestMethod.GET)
    public @ResponseBody
    EventAjax changeStatus(@PathVariable("status") String status, @PathVariable("idEvent") int idEvent,
                                 @PathVariable("idStaff") int idStaff){
        return eventService.changeStatus(status,idEvent);
    }
}
