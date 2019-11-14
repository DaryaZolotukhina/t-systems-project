package hospital.controller;

import hospital.exception.DischargeException;
import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import hospital.model.ProcMed;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(value = "/dischargePatient/{id}", method = RequestMethod.GET)
    @ResponseBody
	public DischargeException dischargePatient(@PathVariable("id") int id) {
		return this.patientService.dischargePatient(id);
	}

	@RequestMapping(value = "/allProcMed", method = RequestMethod.GET)
	@ResponseBody
	public List<ProcMed> getAllProcMed() {
		return patientService.getAllProcMed();
		/*List<String> list=new ArrayList<>();
		list.add("csa");
		list.add("dcsa");
		list.add("fdxz");
		return list;*/
	}

	@RequestMapping(value = "/createPrescription/{id}", method = RequestMethod.GET)
	public String createPresc(@PathVariable("id") int id) {
		return "createPrescription";
	}


	@RequestMapping(value = "/prescription/{idPat}/{idPresc}", method = RequestMethod.GET)
	public @ResponseBody
	List<Event> generateEvents(@PathVariable("idPat") int idPat, @PathVariable("idPresc") int idPresc){
		this.patientService.generateEvents(idPresc);
		return this.patientService.getAllEvents(idPat);
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

	@RequestMapping(value= "/init/{page_id}", method= RequestMethod.GET)
	public String paginate(@PathVariable int page_id, Model model) {
		int total = 5;
		if(page_id == 1) {
			// do nothing!
		} else {
			page_id= (page_id-1)*total+1;
		}

		List<Patient> list = this.patientService.getPatientsByPage(page_id, total);
		model.addAttribute("patient", new Patient());
		model.addAttribute("listPatients",list);
		model.addAttribute("pageId", page_id);

		return "patientsByPages";
	}

	@RequestMapping(value= "/sortSurname/{page_id}/{order}", method= RequestMethod.GET)
	public @ResponseBody List<Patient> sortSurname(@PathVariable int page_id,@PathVariable String  order) {
		int total = 5;
		if(page_id == 1) {
			// do nothing!
		} else {
			page_id= (page_id-1)*total+1;
		}
		return patientService.sortSurname(page_id, order);
	}

	@RequestMapping(value= "/sortEventsDate/{idPat}/{order}", method= RequestMethod.GET)
	public @ResponseBody List<Event> sortEventsDate(@PathVariable("idPat") int idPat,@PathVariable String  order) {
		return patientService.sortEventsDate(order,idPat);
	}

	}
