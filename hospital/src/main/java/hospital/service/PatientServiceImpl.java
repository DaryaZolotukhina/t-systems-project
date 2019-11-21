package hospital.service;

import java.util.ArrayList;
import java.util.List;

import hospital.dao.PrescriptionDAO;
import hospital.dto.PatientDto;
import hospital.exception.DischargeException;
import hospital.model.*;
import hospital.service.utils.CalculatingBitMasks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;


@Service
public class PatientServiceImpl implements PatientService {

    private CalculatingBitMasks calculatingBitMasks;
	private PrescriptionService prescriptionService;
	private EventService eventService;
	private PatientDAO patientDAO;
	private PrescriptionDAO prescriptionDAO;
	private DischargeException dischargeException;

    private static final int NUMBER_OF_RESULTS_PER_PAGE = 5;

    public void setCalculatingBitMasks(CalculatingBitMasks calculatingBitMasks) {
        this.calculatingBitMasks= calculatingBitMasks;
    }

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

<<<<<<< Updated upstream
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
                default:
                    return null;
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
                default:
                    return null;
            }
        }
        return result;
    }

=======
>>>>>>> Stashed changes

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
	        p.setDaySchedule(calculatingBitMasks.dayToBitMask(daySchedule));
        if (weekSchedule.isEmpty()){
            p.setWeekSchedule(0);
        }
        else
            p.setWeekSchedule(calculatingBitMasks.weekToBitMask(weekSchedule));
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
