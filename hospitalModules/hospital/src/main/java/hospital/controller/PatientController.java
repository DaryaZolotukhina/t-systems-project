package hospital.controller;

import hospital.dto.*;
import hospital.exception.DischargeException;
import hospital.model.*;
import hospital.service.EventService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class PatientController {
	
	private PatientService patientService;
	private PrescriptionService prescriptionService;

	@Autowired(required=true)
	@Qualifier(value="prescriptionService")
	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService= prescriptionService;
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
	public String getById(@PathVariable("id") int id, Model model){
		model.addAttribute("patient", patientService.getById(id));
		model.addAttribute("prescriptions",prescriptionService.getAllPrescriptions(id));
		model.addAttribute("prescription",new Prescription());
		model.addAttribute("events",eventService.getAllEvents(id));
		model.addAttribute("event",new Event());
		return "showPatient";
	}

	/*@RequestMapping(value = "/dischargePatient/{id}", method = RequestMethod.GET)
	public String dischargePatient(@PathVariable("id") int id) throws DischargeException
	{
		List<Prescription>list=patientService.dischargePatient(id);
		if (list!=null)
			throw new DischargeException(list);
		else
		return "redirect:/init/1";
	}*/

	@RequestMapping(value = "/dischargePatient/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ErrorMessage dischargePatient(@PathVariable("id") int id) {
		return patientService.dischargePatient(id);
	}

	@RequestMapping(value = "/allDoctors", method = RequestMethod.GET)
	@ResponseBody
	public List<StaffDto> getAllDoctors() {
		return patientService.getAllDoctors();
	}

	@RequestMapping(value = "/doctorsForProcedure/{procedure}", method = RequestMethod.GET)
	@ResponseBody
	public List<StaffDto> getDoctorsForProcedure(@PathVariable("procedure") String procedure)
	{
		return patientService.getDoctorsForProcedure(procedure);
	}

	@RequestMapping(value = "/allDiagnosisType", method = RequestMethod.GET)
	@ResponseBody
	public List<DiagnosisTypeTitleDto> getAllDiagnosisType() {
		return patientService.getAllDiagnosisType();
	}

	@RequestMapping(value = "/allProcedureForDiagnosis/{diagTypeTitle}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProcedureTitleDto> getAllProcedureForDiagnosis(@PathVariable("diagTypeTitle") String diagTypeTitle) {
		return patientService.getAllProcedureForDiagnosis(diagTypeTitle);
	}

	@RequestMapping(value = "/allMedicineForDiagnosis/{diagTypeTitle}", method = RequestMethod.GET)
	@ResponseBody
	public List<MedicineTitleDto> getAllMedicineForDiagnosis(@PathVariable("diagTypeTitle") String diagTypeTitle) {
		return patientService.getAllMedicineForDiagnosis(diagTypeTitle);
	}


	@RequestMapping(value = "/createPrescription/{id}", method = RequestMethod.POST)
	public String createPresc(@PathVariable("id") int id,
									@RequestParam("periodSelect") String period,
									@RequestParam(value="daySchedule",defaultValue="") List<String> daySchedule,
									@RequestParam(value="weekSchedule",defaultValue="") List<String> weekSchedule,
							  @RequestParam("diagnosisType") String diagnosisType,
							  @RequestParam("diagnosis") String diagnosis,
							  @RequestParam(value="procedureSelect",defaultValue="") String procedure,
							  @RequestParam(value="medicineSelect",defaultValue="") String medicine) {

		patientService.addDiagnosis(diagnosis,diagnosisType);
		patientService.addPrescription(id, diagnosis, procedure, medicine, period, daySchedule, weekSchedule);

        return "redirect:/patient/{id}";
	}


	@RequestMapping(value= "/patient/add", method = RequestMethod.GET)
	public String getCreatePatientPage(){

		return "createPatient";
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.POST)
	public String addPatient(@RequestParam("surname") String surname,
							 @RequestParam("name") String name,
							 @RequestParam("patronymic") String patronymic,
							 @RequestParam("insuranceNum") String insuranceNum,
							 @RequestParam("doctor") String doctor){

		patientService.addPatient(surname, name, patronymic, insuranceNum, doctor);
		return "redirect:/patients";
		
	}
	
	@RequestMapping(value="/remove/{id}",method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("id") int id){
		
        patientService.delete(id);
        return "redirect:/patients";
    }

	@RequestMapping(value="/updatePatient",method = RequestMethod.POST)
	public String updatePatient(@RequestParam("surname") String surname,
								@RequestParam("name") String name,
								@RequestParam("patronymic") String patronymic,
								@RequestParam("insuranceNum") String insuranceNum,
								@RequestParam("doctor") String doctor,
								@RequestParam("id") int id){
		patientService.updatePatient(id, surname, name, patronymic, insuranceNum, doctor);
		return "redirect:/patients";
	}

	@RequestMapping(value="/updateDeletePatient/{id}")
	public String updateDeletePatient(@PathVariable("id") int id){
		patientService.updateDeletePatient(id);
		return "redirect:/patients";
	}

	@RequestMapping(value="/update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model){
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
	public @ResponseBody List<Event> sortEventsDate(@PathVariable("idPat") int idPat,@PathVariable String  order) {
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
