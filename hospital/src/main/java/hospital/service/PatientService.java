package hospital.service;

import java.util.List;

import hospital.dto.PatientDto;
import hospital.exception.DischargeException;
import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;

public interface PatientService {

	PatientDto getById(int id);

	void addPatient(Patient p);

	void updatePatient(Patient p);

	void delete(int id);

	List<PatientDto> getAll();

	List<Event> generateEvents(int id);

	List<Prescription> getAllPrescriptions(int id);

	List<Event> getAllEvents(int id);

	Prescription getPrescriptionById(int id);

	void updateDeletePatient(int id);

	void deletePrescription(Prescription presc);

	void deleteEvent(Event event);

	DischargeException dischargePatient(int id);

	void updateDeleteEvent(Event event);

	void updateDeletePrescription(Prescription presc);

	List<Patient> getPatientsByPage(int pageid, int total);
	
}
