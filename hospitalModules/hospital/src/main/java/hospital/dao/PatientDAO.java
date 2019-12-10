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

	public List<Procedure> getAllProcedure();

	Procedure getProcedureByTitle(String title);

	List<Event> sortEventsDate(String order, int id);

	Medicine getMedicineByTitle(String title);

	List<Medicine> getAllMedicine();

	List<DiagnosisType> getAllDiagnosisType();

	DiagnosisType getDiagnosisTypeByTitle(String title);

	void addDiagnosis(Diagnosis d);

	Diagnosis getDiagnosisByTitle(String title);
}
