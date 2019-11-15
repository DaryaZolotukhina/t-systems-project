package hospital.dao;

import java.util.List;

import hospital.model.*;

public interface PatientDAO {

	Patient getById(int id);

	void addPatient(Patient p);

	void updatePatient(Patient p);

	void delete(int id);

	List<Patient> getAll();

	public void saveEvent(Event event);

	StatusEvent getStatusEventById(int id);

	Prescription getPrescriptionById(int id);

	List<Prescription> getAllPrescriptions(int id);

	List<Event> getAllEvents(int id);

	void updatePrescription(Prescription presc);

	void deletePrescription(Prescription presc);

	void deleteEvent(Event event);

	void updateEvent(Event event);

	List<Patient> getPatientsByPage(int pageid, int total);

	List<Patient> sortSurname(int pageid, String order);

	List<Event> sortEventsDate(String order, int id);

	List<ProcMed> getAllProcMed();

	ProcMed getProcMedByTitle(String title);

    void addPresc(Prescription p);
}
