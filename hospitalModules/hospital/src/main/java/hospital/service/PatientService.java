package hospital.service;

import java.math.BigInteger;
import java.util.List;

import hospital.dto.*;
import hospital.dto.patient.CreatePatientRequest;
import hospital.dto.patient.PatientDto;
import hospital.dto.patient.UpdatePatientRequest;
import hospital.dto.prescription.CreatePrescriptionRequest;
import hospital.model.*;

public interface PatientService {

	PatientDto getById(BigInteger id);

	void delete(BigInteger id);

	List<PatientDto> getAll();

	void updateDeletePatient(BigInteger id);

	List<Patient> getPatientsByPage(int pageid);

	List<Patient> sortSurname(int pageid, String order);

	void addPatient(CreatePatientRequest patientRequest);

	void addPrescription(BigInteger id, CreatePrescriptionRequest prescriptionRequest);

	List<Event> sortEventsDate(String order,BigInteger id);

	ErrorMessage dischargePatient(BigInteger id);

	void updatePatient(UpdatePatientRequest patientRequest);
}
