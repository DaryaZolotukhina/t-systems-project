package hospital.controller;

import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientController {
	
	private PatientService patientService;
	
	@Autowired(required=true)
	@Qualifier(value="patientService")
	public void setPatientService(PatientService ps){
		this.patientService = ps;
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public String listPatients(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("listPatients", this.patientService.getAll());
		return "patients";
	}

	@RequestMapping(value = "/patient/{id}",method = RequestMethod.GET)
	public String getById(@PathVariable("id") int id, Model model){
		model.addAttribute("patient", this.patientService.getById(id));
		model.addAttribute("prescriptions",this.patientService.getAllPrescriptions(id));
		model.addAttribute("prescription",new Prescription());
		model.addAttribute("events",this.patientService.getAllEvents(id));
		model.addAttribute("event",new Event());
		return "showPatient";
	}

	@RequestMapping("/prescription/{idPat}/{idPresc}")
	public String generateEvents(@PathVariable("idPat") int idPat,@PathVariable("idPresc") int idPresc, Model model){
		model.addAttribute("generatedEvents", this.patientService.generateEvents(idPresc));
		model.addAttribute("events",this.patientService.getAllEvents(idPat));
		model.addAttribute("event", new Event());
		return "redirect:/patient/{idPat}";
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.GET)
	public String addPatient(){

		return "createPatient";
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.POST)
	public String addPatient(@ModelAttribute("patient") Patient p){

		this.patientService.addPatient(p);
		return "redirect:/patients";
		
	}
	
	@RequestMapping(value="/remove/{id}",method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("id") int id){
		
        this.patientService.delete(id);
        return "redirect:/patients";
    }

	@RequestMapping(value="/updatePatient",method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("patient") Patient p){
		this.patientService.updatePatient(p);
		return "redirect:/patients";
	}

	@RequestMapping(value="/updateDeletePatient/{id}")
	public String updateDeletePatient(@PathVariable("id") int id){
		this.patientService.updateDeletePatient(id);
		return "redirect:/patients";
	}

	@RequestMapping(value="/update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model){
		model.addAttribute("patient",this.patientService.getById(id));
		return "editPatient";
	}
	
}
