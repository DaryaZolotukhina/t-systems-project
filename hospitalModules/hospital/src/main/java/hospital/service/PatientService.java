package hospital.service;

import java.util.List;

import hospital.dto.*;
import hospital.dto.patient.CreatePatientRequest;
import hospital.dto.patient.PatientDto;
import hospital.dto.patient.UpdatePatientRequest;
import hospital.dto.prescription.CreatePrescriptionRequest;
import hospital.model.*;

public interface PatientService {

	PatientDto getById(int id);

	void delete(int id);

	List<PatientDto> getAll();

	void updateDeletePatient(int id);

	List<Patient> getPatientsByPage(int pageid);

	List<Patient> sortSurname(int pageid, String order);

	void addPatient(CreatePatientRequest patientRequest);

	void addPrescription(int id, CreatePrescriptionRequest prescriptionRequest);

	List<Event> sortEventsDate(String order, int id);

	ErrorMessage dischargePatient(int id);

	void updatePatient(UpdatePatientRequest patientRequest);
}
