package hospital.service;

import java.util.List;

import hospital.dto.PatientDto;
import hospital.model.Patient;

public interface PatientService {

	PatientDto getById(int id);

	void addPatient(Patient p);
	void updatePatient(Patient p);

	void delete(int id);

	List<PatientDto> getAll();
	
}
