package hospital.dao;

import java.util.List;

import hospital.model.*;

public interface PatientDAO {

	Patient getById(int id);

	void addPatient(Patient p);

	void updatePatient(Patient p);

	void delete(int id);

	List<Patient> getAll();

	List<Patient> getPatientsByPage(int pageid, int total);

	List<Patient> sortSurname(int pageid, String order);

	List<Event> sortEventsDate(String order, int id);

}
