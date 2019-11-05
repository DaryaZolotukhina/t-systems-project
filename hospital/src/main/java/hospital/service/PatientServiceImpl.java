package hospital.service;

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
    public List<Event> getAllEvents(int id){
        List<Event> events=this.patientDAO.getAllEvents(id);
		return events;
	}

    @Override
    @Transactional
    public List<Prescription> getAllPrescriptions(int id){
        List<Prescription> prescriptions=this.patientDAO.getAllPrescriptions(id);
        return prescriptions;
    }

    @Override
    public Prescription getPrescriptionById(int id){
        Prescription prescription = this.patientDAO.getPrescriptionById(id);
        return prescription;
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

	@Override
	@Transactional
	public List<Event> generateEvents(int id) {
		Prescription presc=this.patientDAO.getPrescriptionById(id);
		List<Event> events=new LinkedList<Event>();
		int period=presc.getPeriod();
		int eventCnt;
		List<Date> dates;
		int day = org.springframework.util.StringUtils.countOccurrencesOf(presc.getDaySchedule(), "1");
		if (day>0){
			eventCnt=period*day;
			dates=calcDateTime(period,presc.getDaySchedule());
		}
		else{
			int week = org.springframework.util.StringUtils.countOccurrencesOf(presc.getWeekSchedule(), "1");
			eventCnt=period*week;
			dates=calcDate(period,presc.getWeekSchedule());
		}
		for(int i=0;i<eventCnt;i++){
			Event event=new Event();
			event.setPatient(presc.getPatient());
			event.setProcMed(presc.getProcMed());
			event.setPrescription(presc);
			event.setDateTimeEvent(dates.get(i));
			event.setStatusEvent(this.patientDAO.getStatusEventById(1));
			this.patientDAO.saveEvent(event);
			events.add(event);
		}
		presc.setIsDone(true);
		this.patientDAO.updatePrescription(presc);
		return events;
	}

}
