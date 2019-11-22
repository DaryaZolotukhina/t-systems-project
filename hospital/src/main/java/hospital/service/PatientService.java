package hospital.service;

import java.util.List;

import hospital.dto.PatientDto;
import hospital.dto.ProcedureMedicineTitleDto;
import hospital.dto.StaffDto;
import hospital.exception.DischargeException;
import hospital.model.*;

public interface PatientService {

	PatientDto getById(int id);

	void updatePatient(int id,String surname, String name, String patronymic, String insuranceNum, String doctor);

	void delete(int id);

	List<PatientDto> getAll();

	void updateDeletePatient(int id);

	DischargeException dischargePatient(int id);

	List<Patient> getPatientsByPage(int pageid);

	List<Patient> sortSurname(int pageid, String order);

	List<ProcedureMedicineTitleDto> getAllProcedureMedicine();

	ProcedureMedicineTitleDto getProcedureMedicineByTitle(String title);

	List<StaffDto> getAllDoctors();

	void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor);

	StaffDto getDoctorBySurname(String surname);

	void addPrescription(int id, String procedureMedicine, String period, List<String> daySchedule, List<String> weekSchedule);

	List<Event> sortEventsDate(String order,int id);

	}
