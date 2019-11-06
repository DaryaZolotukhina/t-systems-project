package hospital.controller;

import hospital.model.Event;
import hospital.service.EventService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService es){
        this.eventService = es;
    }

    @RequestMapping("/prescription/{idPat}/{idPresc}")
    public String generateEvents(@PathVariable("idPat") int idPat, @PathVariable("idPresc") int idPresc, Model model){
        model.addAttribute("generatedEvents", this.eventService.generateEvents(idPresc));
        model.addAttribute("events",this.eventService.getAllEvents(idPat));
        model.addAttribute("event", new Event());
        return "redirect:/patient/{idPat}";
    }

}
