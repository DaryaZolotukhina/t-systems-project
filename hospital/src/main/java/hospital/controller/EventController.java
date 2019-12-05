package hospital.controller;

import hospital.dto.EventAjax;
import hospital.dto.EventDto;
import hospital.dto.EventUIDto;
import hospital.model.Event;
import hospital.model.Prescription;
import hospital.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/events/{idStaff}", method = RequestMethod.GET)
    public @ResponseBody List<EventAjax> eventsForStaff(@PathVariable("idStaff") int id){
       return eventService.eventsForStaff(id);
        //model.addAttribute("event",new Event());
        //return "eventsForStaff";
    }

   /* @RequestMapping(value = "/events/{idStaff}", method = RequestMethod.GET)
    public String eventsForStaff(@PathVariable("idStaff") int id, Model model){
        model.addAttribute("events",eventService.eventsForStaff(id));
        model.addAttribute("event",new Event());
        return "eventsForStaff";
    }*/

    @RequestMapping(value = "/changeStatus/{idEvent}/{idStaff}/{status}", method = RequestMethod.GET)
    public @ResponseBody
    List<EventAjax> changeStatus(@PathVariable("status") String status, @PathVariable("idEvent") int idEvent,
                                @PathVariable("idStaff") int idStaff){
        eventService.changeStatus(status,idEvent);
        return eventService.eventsForStaff(idStaff);
    }
}
