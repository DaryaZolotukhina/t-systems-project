package hospital.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.dto.PatientDto;
import hospital.exception.DischargeException;
import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;

import static hospital.utils.Utils.calcDate;
import static hospital.utils.Utils.calcDateTime;

@Service
public class PatientServiceImpl implements PatientService {
	
	private PatientDAO patientDAO;
	private DischargeException dischargeException;

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setDischargeException(DischargeException dischargeException) {
		this.dischargeException = dischargeException;
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
		List<PatientDto> listPatDto=new ArrayList<PatientDto>();
		List<Patient> listPat= this.patientDAO.getAll();
		for (Patient patient : listPat){
			PatientDto patientDto=new PatientDto();
			patientDto.setId(patient.getId());
			patientDto.setSurname(patient.getSurname());
			patientDto.setName(patient.getName());
			patientDto.setPatronymic(patient.getPatronymic());
            patientDto.setPatDiag(patient.getPatDiag());
            patientDto.setIsDeleted(patient.getIsDeleted());
			patientDto.setIsDischarged(patient.getIsDischarged());
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
        patientDto.setIsDischarged(patient.getIsDischarged());
		return patientDto;
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.patientDAO.delete(id);
	}

	@Override
	@Transactional
	public void deletePrescription(Prescription presc){
		this.patientDAO.deletePrescription(presc);
	};

	@Override
	@Transactional
	public void deleteEvent(Event event){
		this.patientDAO.deleteEvent(event);
	};

	@Override
	@Transactional
	public void updateDeletePrescription(Prescription presc) {
		//Patient patient = this.patientDAO.getById(id);
		presc.setIsDeleted(true);
		this.patientDAO.updatePrescription(presc);
	}

	@Override
	@Transactional
	public void updateDeleteEvent(Event event) {
		//Patient patient = this.patientDAO.getById(id);
		event.setIsDeleted(true);
		this.patientDAO.updateEvent(event);
	}

	@Override
	@Transactional
	public DischargeException dischargePatient(int id) {
		List<Prescription> notDonePrescList = new ArrayList<>();
		List<Prescription> prescList = getAllPrescriptions(id);
		List<Event> eventsList = getAllEvents(id);
		for (Prescription presc : prescList) {
			if (!presc.getIsDone()) {
				notDonePrescList.add(presc);
			}
		}
		if (notDonePrescList.size() > 0) {
			return this.dischargeException.error(notDonePrescList);
		} else {
			for (Event event : eventsList) {
				updateDeleteEvent(event);
			}
			for (Prescription presc : prescList) {
				updateDeletePrescription(presc);
			}
			Patient patient = this.patientDAO.getById(id);
			patient.setIsDischarged(true);
			this.patientDAO.updatePatient(patient);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<Event> generateEvents(int id) {
		Prescription presc=this.patientDAO.getPrescriptionById(id);
		List<Event> events=new ArrayList<Event>();
		int period=presc.getPeriod();
		int eventCnt;
		List<Date> dates;
		int day = presc.getDaySchedule();
		if (day>0){
			dates=calcDateTime(period,presc.getDaySchedule());
			eventCnt=dates.size();
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

	@Override
	public List<Patient> getPatientsByPage(int pageid, int total) {
		List<Patient> list=this.patientDAO.getPatientsByPage(pageid, total);
		return list;
	}

}
