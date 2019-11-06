package hospital.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import hospital.dto.PatientDto;
import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;

import static hospital.utils.Utils.calcDate;
import static hospital.utils.Utils.calcDateTime;

@Service
public class PatientServiceImpl implements PatientService {
	
	private PatientDAO patientDAO;

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	@Override
	@Transactional
	public void addPatient(Patient p) {
		this.patientDAO.addPatient(p);
	}

	@Override
	@Transactional
	public void updatePatient(Patient p) {
		this.patientDAO.updatePatient(p);
	}

    @Override
    @Transactional
    public void updateDeletePatient(int id) {
        Patient patient = this.patientDAO.getById(id);
        patient.setIsDeleted(true);
	    this.patientDAO.updatePatient(patient);
    }

	@Override
	@Transactional
	public List<PatientDto> getAll() {
		List<PatientDto> listPatDto=new LinkedList<PatientDto>();
		List<Patient> listPat= this.patientDAO.getAll();
		for (Patient patient : listPat){
			PatientDto patientDto=new PatientDto();
			patientDto.setId(patient.getId());
			patientDto.setSurname(patient.getSurname());
			patientDto.setName(patient.getName());
			patientDto.setPatronymic(patient.getPatronymic());
            patientDto.setPatDiag(patient.getPatDiag());
            patientDto.setIsDeleted(patient.getIsDeleted());
			listPatDto.add(patientDto);
		}
		return listPatDto;
	}

	@Override
	@Transactional
	public PatientDto getById(int id) {
		PatientDto patientDto=new PatientDto();
		Patient patient = this.patientDAO.getById(id);
		patientDto.setId(patient.getId());
		patientDto.setSurname(patient.getSurname());
		patientDto.setName(patient.getName());
		patientDto.setPatronymic(patient.getPatronymic());
		patientDto.setPrescriptions(patient.getPrescriptions());
		patientDto.setInsuranceNum(patient.getInsuranceNum());
        patientDto.setPatDiag(patient.getPatDiag());
        patientDto.setIsDeleted(patient.getIsDeleted());
		return patientDto;
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.patientDAO.delete(id);
	}

}
