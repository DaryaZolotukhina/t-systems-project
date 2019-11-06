package hospital.dao;

import java.util.List;

import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import hospital.model.StatusEvent;

public interface PatientDAO {

	Patient getById(int id);

	void addPatient(Patient p);

	void updatePatient(Patient p);

	void delete(int id);

	List<Patient> getAll();

}
