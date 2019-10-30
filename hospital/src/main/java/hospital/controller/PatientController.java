package hospital.controller;

import hospital.model.Patient;
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
		return "patient";
	}

	@RequestMapping(value= "/patient/add", method = RequestMethod.POST)
	public String addPatient(@ModelAttribute("patient") Patient p){
		
		if(p.getId() == 0){
			this.patientService.addPatient(p);
		}else{
			this.patientService.updatePatient(p);
		}
		
		return "redirect:/patients";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.patientService.delete(id);
        return "redirect:/patients";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("patient", this.patientService.getById(id));
        model.addAttribute("listPatients", this.patientService.getAll());
        return "patient";
    }
	
}
