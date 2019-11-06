package hospital.service;

import java.util.List;

import hospital.dto.PatientDto;
import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;

public interface PatientService {

	PatientDto getById(int id);

	void addPatient(Patient p);

	void updatePatient(Patient p);

	void delete(int id);

	List<PatientDto> getAll();

	void updateDeletePatient(int id);
	
}
