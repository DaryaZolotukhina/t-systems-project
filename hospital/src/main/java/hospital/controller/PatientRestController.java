package hospital.controller;

import hospital.dto.PatientDto;
import hospital.model.Patient;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/")
public class PatientRestController {

    private PatientService patientService;

    @Autowired(required = true)
    @Qualifier(value = "patientService")
    public void setPatientService(PatientService ps) {
        this.patientService = ps;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<PatientDto> getPatient(@PathVariable("id") Integer patientId) {
        if (patientId == null) {
            return new ResponseEntity<PatientDto>(HttpStatus.BAD_REQUEST);
        }
        PatientDto patient = this.patientService.getById(patientId);


        return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient p) {
        /*if (p.getId() == 0){*/
            this.patientService.addPatient(p);
            return new ResponseEntity<Patient>(p,HttpStatus.CREATED);
       /* }else{
            //existing person, call update
            this.patientService.updatePatient(p);
            return new ResponseEntity<Patient>(p, HttpStatus.OK);
        }*/

    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") int id){
        PatientDto patient=this.patientService.getById(id);

        this.patientService.delete(id);

        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<PatientDto> patients= this.patientService.getAll();
        return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
    }
}
