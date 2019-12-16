package hospital.controller;

import hospital.dto.*;
import hospital.dto.patient.CreatePatientRequest;
import hospital.dto.patient.UpdatePatientRequest;
import hospital.dto.prescription.CreatePrescriptionRequest;
import hospital.model.*;
import hospital.service.DiagnosisService;
import hospital.service.EventService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
	
	private PatientService patientService;
	private PrescriptionService prescriptionService;
	private DiagnosisService diagnosisService;

	@Autowired(required=true)
	@Qualifier(value="prescriptionService")
	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService= prescriptionService;
	}

	@Autowired(required=true)
	@Qualifier(value="diagnosisService")
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService= diagnosisService;
	}

	@Autowired(required=true)
	@Qualifier(value="patientService")
	public void setPatientService(PatientService ps){
		this.patientService = ps;
	}

	private EventService eventService;

	@Autowired(required=true)
	@Qualifier(value="eventService")
	public void setEventService(EventService eventService) {
		this.eventService= eventService;
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public String listPatients(Model model) {
		return "redirect:/init/1";
	}

	@RequestMapping(value = "/patient/{id}",method = RequestMethod.GET)
	public String getById(@PathVariable("id") BigInteger id, Model model){
		model.addAttribute("patient", patientService.getById(id));
		model.addAttribute("prescriptions",prescriptionService.getAllPrescriptions(id));
		model.addAttribute("prescription",new Prescription());
		model.addAttribute("events",eventService.getAllEvents(id));
		model.addAttribute("event",new Event());
		return "showPatient";
	}

	@RequestMapping(value = "/patient/discharge/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ErrorMessage dischargePatient(@PathVariable("id") BigInteger id) {
		return patientService.dischargePatient(id);
	}

	@RequestMapping(value = "/prescriptions/{id}", method = RequestMethod.POST)
	public @ResponseBody CreatePrescriptionRequest createPresc(@PathVariable("id") BigInteger id,
															   @Valid @RequestBody CreatePrescriptionRequest prescriptionRequest) {
		diagnosisService.addDiagnosis(prescriptionRequest.getDiagnosis(),prescriptionRequest.getDiagnosisType());
		patientService.addPrescription(id, prescriptionRequest);

		return prescriptionRequest;
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.GET)
	public String getCreatePatientPage(){

		return "createPatient";
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.POST)
	public @ResponseBody
	CreatePatientRequest addPatient(@Valid @RequestBody CreatePatientRequest patientRequest){

		patientService.addPatient(patientRequest);
		return patientRequest;

	}
	
	@RequestMapping(value="/patient/remove/{id}",method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("id") BigInteger id){
		
        patientService.delete(id);
        return "redirect:/patients";
    }

	@RequestMapping(value="/patient/update/{id}",method = RequestMethod.POST)
	public @ResponseBody UpdatePatientRequest updatePatient(@Valid @RequestBody UpdatePatientRequest patientRequest,
								@PathVariable("id") BigInteger id){
		patientService.updatePatient(patientRequest);
		return patientRequest;
	}

	@RequestMapping(value="/patient/delete/{id}",method = RequestMethod.GET)
	public String updateDeletePatient(@PathVariable("id") BigInteger id){
		patientService.updateDeletePatient(id);
		return "redirect:/patients";
	}

	@RequestMapping(value="/patient/update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable("id") BigInteger id, Model model){
		model.addAttribute("patient",patientService.getById(id));
		return "editPatient";
	}

	@RequestMapping(value= "/init/{page_id}", method= RequestMethod.GET)
	public String paginate(@PathVariable int page_id, Model model) {
		List<Patient> list = patientService.getPatientsByPage(page_id);
		model.addAttribute("patient", new Patient());
		model.addAttribute("listPatients",list);
		model.addAttribute("pageId", page_id);

		return "patientsByPages";
	}

	@RequestMapping(value= "/sortSurname/{page_id}/{order}", method= RequestMethod.GET)
	public @ResponseBody List<Patient> sortSurname(@PathVariable int page_id,@PathVariable String  order) {
		return patientService.sortSurname(page_id, order);
	}

	@RequestMapping(value= "/sortEventsDate/{idPat}/{order}", method= RequestMethod.GET)
	public @ResponseBody List<Event> sortEventsDate(@PathVariable("idPat") BigInteger idPat,@PathVariable String  order) {
		return patientService.sortEventsDate(order,idPat);
	}

	@RequestMapping(value ="/welcome", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}
		model.setViewName("welcomePage");
		return model;

	}

	}
