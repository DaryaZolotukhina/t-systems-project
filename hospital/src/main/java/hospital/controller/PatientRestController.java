package hospital.controller;

import hospital.model.Patient;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "{id}", method= RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long patientId){
        if(patientId == null){
            return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }
        Patient patient=this.patientService.getById(patientId);

        if(patient==null){
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }


    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
        if(patient==null){
           return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }

        this.patientService.save(patient);
        return new ResponseEntity<Patient>(patient,HttpStatus.CREATED);
    }


    @RequestMapping(value="", method=RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        if(patient==null){
            return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }

        this.patientService.save(patient);

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Long id){
        Patient patient=this.patientService.getById(id);

        if(patient==null){
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }

        this.patientService.delete(id);

        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patients= this.patientService.getAll();

        if(patients.isEmpty()){
            return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }

}
