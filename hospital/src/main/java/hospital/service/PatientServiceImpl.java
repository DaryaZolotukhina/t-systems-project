package hospital.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hospital.dao.PrescriptionDAO;
import hospital.dto.PatientDto;
import hospital.exception.DischargeException;
import hospital.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;


@Service
public class PatientServiceImpl implements PatientService {

	private PrescriptionService prescriptionService;
	private EventService eventService;
	private PatientDAO patientDAO;
	private PrescriptionDAO prescriptionDAO;
	private DischargeException dischargeException;

    private static final int NUMBER_OF_RESULTS_PER_PAGE = 5;

	public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
		this.prescriptionDAO = prescriptionDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService= prescriptionService;
	}

	public void setEventService(EventService eventService) {
		this.eventService= eventService;
	}

	public void setDischargeException(DischargeException dischargeException) {
		this.dischargeException = dischargeException;
	}

    @Override
    @Transactional
    public void updateDeletePatient(int id) {
        Patient patient = patientDAO.getById(id);
        patient.setIsDeleted(true);
	    patientDAO.updatePatient(patient);
    }

	@Override
	@Transactional
	public List<PatientDto> getAll() {
		List<PatientDto> listPatDto=new ArrayList<>();
		List<Patient> listPat= patientDAO.getAll();
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
		Patient patient = patientDAO.getById(id);
		patientDto.setId(patient.getId());
		patientDto.setSurname(patient.getSurname());
		patientDto.setName(patient.getName());
		patientDto.setPatronymic(patient.getPatronymic());
		patientDto.setPrescriptions(patient.getPrescriptions());
		patientDto.setInsuranceNum(patient.getInsuranceNum());
        patientDto.setPatDiag(patient.getPatDiag());
        patientDto.setIsDeleted(patient.getIsDeleted());
        patientDto.setIsDischarged(patient.getIsDischarged());
        patientDto.setStaff(patient.getStaff());
		return patientDto;
	}

    @Override
    @Transactional
    public Patient getByIdPatient(int id) {
        return patientDAO.getById(id);
    }

	@Override
	@Transactional
	public void delete(int id) {
		patientDAO.delete(id);
	}

	@Override
	@Transactional
	public DischargeException dischargePatient(int id) {
		List<Prescription> notDonePrescList = new ArrayList<>();
		List<Prescription> prescList = prescriptionService.getAllPrescriptions(id);
		List<Event> eventsList = eventService.getAllEvents(id);
		for (Prescription presc : prescList) {
			if (!presc.getIsDone()) {
				notDonePrescList.add(presc);
			}
		}
		if (! notDonePrescList.isEmpty()) {
			return dischargeException.error(notDonePrescList);
		} else {
			for (Event event : eventsList) {
				eventService.updateDeleteEvent(event);
			}
			for (Prescription presc : prescList) {
				prescriptionService.updateDeletePrescription(presc);
			}
			Patient patient = patientDAO.getById(id);
			patient.setIsDischarged(true);
			patientDAO.updatePatient(patient);
		}
		return null;
	}

	@Override
    @Transactional
    public ProcedureMedicine getProcedureMedicineByTitle(String title) {
        return patientDAO.getProcedureMedicineByTitle(title);
    }

	@Override
	@Transactional
	public Staff getDoctorBySurname(String surname) {
		return patientDAO.getDoctorBySurname(surname);
	}

    public Integer weekToBitMask(List<String> weeks) {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        int result=0;
        for (String str : weeks) {
            for (int i = 0; i < dayOfWeeks.length; i++) {
                if (str.toUpperCase().equals(dayOfWeeks[i].toString())){
                    result += (int) Math.pow(2, i);
                }
            }
        }
        return result;
    }

    enum Hours {
        h0("00:00"),
        h1("01:00"),
        h2("02:00"),
        h3("03:00"),
        h4("04:00"),
        h5("05:00"),
        h6("06:00"),
        h7("07:00"),
        h8("08:00"),
        h9("09:00"),
        h10("10:00"),
        h11("11:00"),
        h12("12:00"),
        h13("13:00"),
        h14("14:00"),
        h15("15:00"),
        h16("16:00"),
        h17("17:00"),
        h18("18:00"),
        h19("19:00"),
        h20("20:00"),
        h21("21:00"),
        h22("22:00"),
        h23("23:00");
        private final String text;

        Hours(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public Integer dayToBitMask(List<String> days) {
	    Hours[] hours = Hours.values();
	    int result=0;
	    for (String str : days) {
	        for (int i = 0; i < hours.length; i++) {
	            if (str.equals(hours[i].toString())){
	                result += (int) Math.pow(2, i);
	            }
	        }
	    }
        return result;
    }


	@Transactional
	@Override
	public List<ProcedureMedicine> getAllProcedureMedicine(){
		return patientDAO.getAllProcedureMedicine();
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
	public List<Patient> getPatientsByPage(int pageid) {
        if(pageid == 1) {
        } else {
            pageid= (pageid-1)*NUMBER_OF_RESULTS_PER_PAGE+1;
        }
	    return patientDAO.getPatientsByPage(pageid, NUMBER_OF_RESULTS_PER_PAGE);
	}


	@Override
	@Transactional
	public List<Patient> sortSurname(int pageid, String order) {
        if(pageid == 1) {
        } else {
            pageid= (pageid-1)*NUMBER_OF_RESULTS_PER_PAGE+1;
        }
	    return patientDAO.sortSurname(pageid, order);
	}

	@Override
	@Transactional
	public List<Event> sortEventsDate(String order,int id){
		return patientDAO.sortEventsDate(order,id);
	}

    @Override
    @Transactional
    public void addPrescription(int id, String procedureMedicine, String period, List<String> daySchedule, List<String> weekSchedule){
	    Prescription p =new Prescription();
	    p.setPatient(getByIdPatient(id));
	    p.setProcedureMedicine(getProcedureMedicineByTitle(procedureMedicine));
	    if (daySchedule.isEmpty()){
	        p.setDaySchedule(0);
        }
	    else
	        p.setDaySchedule(dayToBitMask(daySchedule));
        if (weekSchedule.isEmpty()){
            p.setWeekSchedule(0);
        }
        else
            p.setWeekSchedule(weekToBitMask(weekSchedule));
        p.setPeriod(Integer.parseInt(period));
        p.setDose((float)1.0);
        p.setIsDeleted(false);
        p.setIsDone(false);

        prescriptionDAO.addPresc(p);

    }

	@Override
	@Transactional
	public void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor){
		Patient p=new Patient();
		p.setSurname(surname);
		p.setName(name);
		p.setPatronymic(patronymic);
		p.setInsuranceNum(insuranceNum);
		Staff staff=getDoctorBySurname(doctor);
		p.setStaff(staff);
		p.setIsDeleted(false);
		p.setIsDischarged(false);

		patientDAO.addPatient(p);
	}

    @Override
    @Transactional
    public void updatePatient(int id,String surname, String name, String patronymic, String insuranceNum, String doctor) {
	    Patient p=getByIdPatient(id);
        p.setSurname(surname);
        p.setName(name);
        p.setPatronymic(patronymic);
        p.setInsuranceNum(insuranceNum);
        Staff staff=getDoctorBySurname(doctor);
        p.setStaff(staff);
        p.setIsDeleted(false);
        p.setIsDischarged(false);
        this.patientDAO.updatePatient(p);
    }
}
