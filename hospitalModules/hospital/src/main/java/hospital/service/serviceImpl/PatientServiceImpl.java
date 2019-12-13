package hospital.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import hospital.dao.EventDAO;
import hospital.dao.PrescriptionDAO;
import hospital.dto.*;
import hospital.dto.patient.CreatePatientRequest;
import hospital.dto.patient.PatientDto;
import hospital.dto.patient.UpdatePatientRequest;
import hospital.dto.prescription.CreatePrescriptionRequest;
import hospital.dto.prescription.PrescriptionDto;
import hospital.dto.prescription.PrescriptionError;
import hospital.mappers.*;
import hospital.exception.DischargeException;
import hospital.model.*;
import hospital.service.*;
import hospital.service.utils.CalculatingBitMasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.PatientDAO;


@Service
public class PatientServiceImpl implements PatientService {

    private CalculatingBitMasks calculatingBitMasks;
	private PrescriptionService prescriptionService;
	private EventService eventService;
	private final PatientDAO patientDAO;
	private EventDAO eventDAO;
	private PrescriptionDAO prescriptionDAO;
	private DoctorService doctorService;
	private DiagnosisService diagnosisService;
	private MedicineService medicineService;
	private ProcedureService procedureService;


	private static final int NUMBER_OF_RESULTS_PER_PAGE = 5;

    @Autowired
	public PatientServiceImpl(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}


    public void setCalculatingBitMasks(CalculatingBitMasks calculatingBitMasks) {
        this.calculatingBitMasks= calculatingBitMasks;
    }

	public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
		this.prescriptionDAO = prescriptionDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService= prescriptionService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService= medicineService;
	}

	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService= procedureService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService= diagnosisService;
	}

	public void setEventService(EventService eventService) {
		this.eventService= eventService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService= doctorService;
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
			listPatDto.add(PatientMapper.PATIENT_MAPPER.fromPatient(patient));
		}
		return listPatDto;
	}

	@Override
	@Transactional
	public PatientDto getById(int id) {
		Patient patient = patientDAO.getById(id);
		return PatientMapper.PATIENT_MAPPER.fromPatient(patient);
	}


	@Override
	@Transactional
	public void delete(int id) {
		patientDAO.delete(id);
	}

	@Override
	@Transactional
	public ErrorMessage dischargePatient(int id){
		List<PrescriptionError> notDonePrescList = new ArrayList<>();
		List<Prescription> prescList = prescriptionService.getAllPrescriptions(id);
		List<Event> listEvent = eventDAO.getAllEvents(id);
		for (Prescription presc : prescList) {
			if (!presc.getIsDone()) {
				notDonePrescList.add(PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescriptionError(presc));
			}
		}
		try {
			if (!notDonePrescList.isEmpty()) {
				throw new DischargeException();
			}
		}
		catch(DischargeException e){
				ErrorMessage errorMessage=new ErrorMessage();
				errorMessage.setErrMsg(e.getMessage());
				errorMessage.setPrescriptionList(notDonePrescList);
				return  errorMessage;
			}
			for (Event event : listEvent) {
				eventService.updateDeleteEvent(event);
			}
			for (Prescription presc : prescList) {
				prescriptionService.updateDeletePrescription(presc);
			}
			Patient patient = patientDAO.getById(id);
			patient.setIsDischarged(true);
			patientDAO.updatePatient(patient);
		return null;
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
    public void addPrescription(int id, CreatePrescriptionRequest prescriptionRequest){
	    PrescriptionDto p =new PrescriptionDto();
		if (prescriptionRequest.getProcedure()==null){
			prescriptionRequest.setProcedure("");
		}
		if (prescriptionRequest.getMedicine()==null){
			prescriptionRequest.setMedicine("");
		}
		if (prescriptionRequest.getWeekSchedule().isEmpty()){
			prescriptionRequest.setWeekSchedule(new ArrayList<>());
		}
		if (prescriptionRequest.getDaySchedule().isEmpty()){
			prescriptionRequest.setDaySchedule(new ArrayList<>());
		}
	    p.setPatient(PatientMapper.PATIENT_MAPPER.toPatient(getById(id)));
	    if (!prescriptionRequest.getProcedure().equals("")) {
            p.setProcedure(procedureService.getProcedureByTitle(prescriptionRequest.getProcedure()));
        }
	    else
	        p.setMedicine(medicineService.getMedicineByTitle(prescriptionRequest.getMedicine()));
	   if (prescriptionRequest.getDaySchedule().isEmpty()){
	        p.setDaySchedule(0);
        }
	    else
	        p.setDaySchedule(calculatingBitMasks.dayToBitMask(prescriptionRequest.getDaySchedule()));
        if (prescriptionRequest.getWeekSchedule().isEmpty()){
            p.setWeekSchedule(0);
        }
        else
            p.setWeekSchedule(calculatingBitMasks.weekToBitMask(prescriptionRequest.getWeekSchedule()));
		Staff staff=StaffMapper.STAFF_MAPPER.toStaff(doctorService.getById(prescriptionRequest.getStaffId()));
		p.setStaff(staff);
        p.setPeriod(Integer.parseInt(prescriptionRequest.getPeriod()));
        p.setDose((float)1.0);
        p.setIsDeleted(false);
        p.setIsDone(false);

        Prescription presc=PrescriptionMapper.PRESCRIPTION_MAPPER.toPrescription(p);
        presc.setDiagnosis(diagnosisService.getDiagnosisByTitle(prescriptionRequest.getDiagnosis()));
        prescriptionDAO.addPresc(presc);

    }

	@Override
	@Transactional
	public void addPatient(CreatePatientRequest patientRequest){
		Patient p=PatientMapper.PATIENT_MAPPER.toPatientfromCreatePatientRequest(patientRequest);
		Staff staff=StaffMapper.STAFF_MAPPER.toStaff(doctorService.getById(patientRequest.getStaffId()));
		p.setStaff(staff);
		p.setIsDeleted(false);
		p.setIsDischarged(false);

		this.patientDAO.addPatient(p);
	}

    @Override
    @Transactional
    public void updatePatient(UpdatePatientRequest patientRequest) {
	    Patient pat=patientDAO.getById(patientRequest.getId());
		pat.setSurname(patientRequest.getSurname());
		pat.setName(patientRequest.getName());
		pat.setPatronymic(patientRequest.getPatronymic());
		pat.setInsuranceNum(patientRequest.getInsuranceNum());
		Staff staff=StaffMapper.STAFF_MAPPER.toStaff(doctorService.getById(patientRequest.getStaffId()));
		pat.setStaff(staff);
		pat.setIsDeleted(false);
		pat.setIsDischarged(false);

        this.patientDAO.updatePatient(pat);
    }
}
