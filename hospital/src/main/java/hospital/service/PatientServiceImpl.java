package hospital.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.dto.PatientDto;
import hospital.exception.DischargeException;
import hospital.model.*;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Patient getByIdPatient(int id) {
        return this.patientDAO.getById(id);
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
    public ProcMed getProcMedByTitle(String title) {
        return patientDAO.getProcMedByTitle(title);
    }

	@Override
	@Transactional
	public Staff getDoctorBySurname(String surname) {
		return patientDAO.getDoctorBySurname(surname);
	}

    public Integer weekToBitMask(List<String> weeks) {
        int result=0;
        for (String str : weeks) {
            switch (str) {
                case ("Monday"):
                    result += (int) Math.pow(2, 0);
                    break;
                case ("Tuesday"):
                    result += (int) Math.pow(2, 1);
                    break;
                case ("Wednesday"):
                    result += (int) Math.pow(2, 2);
                    break;
                case ("Thursday"):
                    result += (int) Math.pow(2, 3);
                    break;
                case ("Friday"):
                    result += (int) Math.pow(2, 4);
                    break;
                case ("Saturday"):
                    result += (int) Math.pow(2, 5);
                    break;
                case ("Sunday"):
                    result += (int) Math.pow(2, 6);
                    break;
            }
        }
        return result;
    }

    public Integer dayToBitMask(List<String> days) {
        int result=0;
        for (String str : days) {
            switch (str) {
                case ("00:00"):
                    result += (int) Math.pow(2, 0);
                    break;
                case ("01:00"):
                    result += (int) Math.pow(2, 1);
                    break;
                case ("02:00"):
                    result += (int) Math.pow(2, 2);
                    break;
                case ("03:00"):
                    result += (int) Math.pow(2, 3);
                    break;
                case ("04:00"):
                    result += (int) Math.pow(2, 4);
                    break;
                case ("05:00"):
                    result += (int) Math.pow(2, 5);
                    break;
                case ("06:00"):
                    result += (int) Math.pow(2, 6);
                    break;
                case ("07:00"):
                    result += (int) Math.pow(2, 7);
                    break;
                case ("08:00"):
                    result += (int) Math.pow(2, 8);
                    break;
                case ("09:00"):
                    result += (int) Math.pow(2, 9);
                    break;
                case ("10:00"):
                    result += (int) Math.pow(2, 10);
                    break;
                case ("11:00"):
                    result += (int) Math.pow(2, 11);
                    break;
                case ("12:00"):
                    result += (int) Math.pow(2, 12);
                    break;
                case ("13:00"):
                    result += (int) Math.pow(2, 13);
                    break;
                case ("14:00"):
                    result += (int) Math.pow(2, 14);
                    break;
                case ("15:00"):
                    result += (int) Math.pow(2, 15);
                    break;
                case ("16:00"):
                    result += (int) Math.pow(2, 16);
                    break;
                case ("17:00"):
                    result += (int) Math.pow(2, 17);
                    break;
                case ("18:00"):
                    result += (int) Math.pow(2, 18);
                    break;
                case ("19:00"):
                    result += (int) Math.pow(2, 19);
                    break;
                case ("20:00"):
                    result += (int) Math.pow(2, 20);
                    break;
                case ("21:00"):
                    result += (int) Math.pow(2, 21);
                    break;
                case ("22:00"):
                    result += (int) Math.pow(2, 22);
                    break;
                case ("23:00"):
                    result += (int) Math.pow(2, 23);
                    break;
            }
        }
        return result;
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
			dates=calcDate(period,presc.getWeekSchedule());
			eventCnt=dates.size();
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

	@Transactional
	@Override
	public List<ProcMed> getAllProcMed(){
		return patientDAO.getAllProcMed();
	}

	@Transactional
	@Override
	public List<Staff> getAllDoctors(){
		List<Staff> staffList=patientDAO.getAllDoctors();
		List<Staff> doctList=new ArrayList<>();
		for(Staff staff:staffList){
			if (staff.getIsDoctor()){
				doctList.add(staff);
			}
		}
		return doctList;
	}

	@Override
	@Transactional
	public List<Patient> getPatientsByPage(int pageid, int total) {
		return patientDAO.getPatientsByPage(pageid, total);
	}


	@Override
	@Transactional
	public List<Patient> sortSurname(int pageid, String order) {
		return patientDAO.sortSurname(pageid, order);
	}

	@Override
	@Transactional
	public List<Event> sortEventsDate(String order,int id){
		return patientDAO.sortEventsDate(order,id);
	}

    @Override
    @Transactional
    public void addPrescription(int id, String procMed, String period, List<String> daySchedule, List<String> weekSchedule){
	    Prescription p =new Prescription();
	    p.setPatient(getByIdPatient(id));
	    p.setProcMed(getProcMedByTitle(procMed));
	    if (daySchedule.size()==0){
	        p.setDaySchedule(0);
        }
	    else
	        p.setDaySchedule(dayToBitMask(daySchedule));
        if (weekSchedule.size()==0){
            p.setWeekSchedule(0);
        }
        else
            p.setWeekSchedule(weekToBitMask(weekSchedule));
        p.setPeriod(Integer.parseInt(period));
        p.setDose((float)0.0);
        p.setIsDeleted(false);
        p.setIsDone(false);

        patientDAO.addPresc(p);

    }

	@Override
	@Transactional
	public void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor){
		Patient p=new Patient();
		p.setSurname(surname);
		p.setName(name);
		p.setPatronymic(patronymic);
		p.setInsuranceNum(insuranceNum);
		p.setStaff(getDoctorBySurname(doctor));
		p.setIsDeleted(false);
		p.setIsDischarged(false);

		patientDAO.addPatient(p);
	}
}
