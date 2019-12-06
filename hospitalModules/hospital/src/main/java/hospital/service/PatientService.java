package hospital.service;

import java.util.List;

import hospital.dto.*;
import hospital.exception.DischargeException;
import hospital.model.*;

public interface PatientService {

	PatientDto getById(int id);

	void updatePatient(int id,String surname, String name, String patronymic, String insuranceNum, String doctor);

	void delete(int id);

	List<PatientDto> getAll();

	void updateDeletePatient(int id);

	List<Patient> getPatientsByPage(int pageid);

	List<Patient> sortSurname(int pageid, String order);

	List<StaffDto> getAllDoctors();

	void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor);

	StaffDto getDoctorBySurname(String surname);

	void addPrescription(int id, String diagnosis, String procedure, String medicine, String period, List<String> daySchedule, List<String> weekSchedule);

	List<Event> sortEventsDate(String order,int id);

	List<DiagnosisTypeTitleDto> getAllDiagnosisType();

	DiagnosisType getDiagnosisTypeByTitle(String title);

	List<ProcedureTitleDto> getAllProcedureForDiagnosis(String titleDiag);

	List<MedicineTitleDto> getAllMedicineForDiagnosis(String titleDiag);

	void addDiagnosis(String title, String diagnosisType);

	Procedure getProcedureByTitle(String title);

	Medicine getMedicineByTitle(String title);

	Diagnosis getDiagnosisByTitle(String title);

	ErrorMessage dischargePatient(int id);
	}
