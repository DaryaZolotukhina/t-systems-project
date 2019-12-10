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

	void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor);

	void addPrescription(int id, String diagnosis, String procedure, String medicine, String period, List<String> daySchedule, List<String> weekSchedule);

	List<Event> sortEventsDate(String order,int id);

	ErrorMessage dischargePatient(int id);
	}
