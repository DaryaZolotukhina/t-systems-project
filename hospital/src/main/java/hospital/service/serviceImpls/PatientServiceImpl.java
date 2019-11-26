package hospital.service.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import hospital.dao.PrescriptionDAO;
import hospital.dto.*;
import hospital.dto.mappers.*;
import hospital.exception.DischargeException;
import hospital.model.*;
import hospital.service.EventService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
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
	public DischargeException dischargePatient(int id) {
		List<Prescription> notDonePrescList = new ArrayList<>();
		List<PrescriptionDto> prescDtoList = prescriptionService.getAllPrescriptions(id);
		List<Prescription> listPrescription=new ArrayList<>();
		for (PrescriptionDto prescriptionDto : prescDtoList){
			listPrescription.add(PrescriptionMapper.PRESCRIPTION_MAPPER.toPrescription(prescriptionDto));
		}
		/*List<EventDto> eventsDtoList = eventService.getAllEvents(id);
		List<Event> listEvent=new ArrayList<>();
		for (EventDto eventDto : eventsDtoList){
			listEvent.add(EventMapper.EVENT_MAPPER.toEvent(eventDto));
		}*/
		for (Prescription presc : listPrescription) {
			if (!presc.getIsDone()) {
				notDonePrescList.add(presc);
			}
		}
		if (! notDonePrescList.isEmpty()) {
			return dischargeException.error(notDonePrescList);
		} else {
			/*for (Event event : listEvent) {
				eventService.updateDeleteEvent(event);
			}*/
			for (Prescription presc : listPrescription) {
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
	public StaffDto getDoctorBySurname(String surname) {
		return StaffMapper.STAFF_MAPPER.fromStaff(patientDAO.getDoctorBySurname(surname));

	}

	@Override
	@Transactional
	public DiagnosisType getDiagnosisTypeByTitle(String title) {
		DiagnosisType diagnosisType=patientDAO.getDiagnosisTypeByTitle(title);
		return diagnosisType;
	}


	@Override
	@Transactional
	public Diagnosis getDiagnosisByTitle(String title) {
		Diagnosis diagnosis=patientDAO.getDiagnosisByTitle(title);
		return diagnosis;
	}

    @Override
    @Transactional
    public Procedure getProcedureByTitle(String title) {
        Procedure procedure=patientDAO.getProcedureByTitle(title);
        return procedure;
    }

    @Override
    @Transactional
    public Medicine getMedicineByTitle(String title) {
        Medicine medicine=patientDAO.getMedicineByTitle(title);
        return medicine;
    }

	@Transactional
	@Override
	public List<ProcedureTitleDto> getAllProcedureForDiagnosis(String titleDiag) {
		List<Procedure> listProc = patientDAO.getAllProcedure();
		List<Procedure> listProcForDiag = new ArrayList<>();
		for (Procedure proc : listProc) {
			for (DiagnosisType diagnosisType : proc.getDiagnosisTypes()){
				if (diagnosisType.getTitle().equals(titleDiag)){
					listProcForDiag.add(proc);
					continue;
				}
			}
		}
		List<ProcedureTitleDto> listProcTitleDto=new ArrayList<>();
		for (Procedure proc : listProcForDiag){
			ProcedureTitleDto procedureTitleDto=new ProcedureTitleDto();
			procedureTitleDto.setId(proc.getId());
			procedureTitleDto.setTitle(proc.getTitle());
			listProcTitleDto.add(procedureTitleDto);
		}
		return listProcTitleDto;
	}

	@Transactional
	@Override
	public List<MedicineTitleDto> getAllMedicineForDiagnosis(String titleDiag) {
        List<Medicine> listMedicine = patientDAO.getAllMedicine();
        List<Medicine> listMedicineForDiag = new ArrayList<>();
        for (Medicine med : listMedicine) {
            for (DiagnosisType diagnosisType : med.getDiagnosisTypes()){
                if (diagnosisType.getTitle().equals(titleDiag)){
                    listMedicineForDiag.add(med);
                    continue;
                }
            }
        }
        List<MedicineTitleDto> listMedicineTitleDto=new ArrayList<>();
        for (Medicine med : listMedicineForDiag){
			MedicineTitleDto medicineTitleDto=new MedicineTitleDto();
			medicineTitleDto.setId(med.getId());
			medicineTitleDto.setTitle(med.getTitle());
            listMedicineTitleDto.add(medicineTitleDto);
        }
        return listMedicineTitleDto;
	}

	@Transactional
	@Override
	public List<DiagnosisTypeTitleDto> getAllDiagnosisType(){
		List<DiagnosisType> listDiagnosisType=patientDAO.getAllDiagnosisType();
		List<DiagnosisTypeTitleDto> listDiagnosisTypeTitleDto=new ArrayList<>();
		for (DiagnosisType diagnosisType : listDiagnosisType){
			DiagnosisTypeTitleDto diag=new DiagnosisTypeTitleDto();
			diag.setId(diagnosisType.getId());
			diag.setTitle(diagnosisType.getTitle());
			listDiagnosisTypeTitleDto.add(diag);
		}
		return listDiagnosisTypeTitleDto;
	}

	@Transactional
	@Override
	public List<StaffDto> getAllDoctors(){
		List<Staff> staffList=patientDAO.getAllDoctors();
		List<StaffDto> listStaffDto=new ArrayList<>();
		for (Staff staff : staffList){
			listStaffDto.add(StaffMapper.STAFF_MAPPER.fromStaff(staff));
		}
		return listStaffDto;
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
    public void addPrescription(int id, String diagnosis, String procedure, String medicine, String period, List<String> daySchedule, List<String> weekSchedule){
	    PrescriptionDto p =new PrescriptionDto();
	    p.setPatient(PatientMapper.PATIENT_MAPPER.toPatient(getById(id)));
	    if (!procedure.equals("")) {
            p.setProcedure(getProcedureByTitle(procedure));
        }
	    else
	        p.setMedicine(getMedicineByTitle(medicine));
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

        Prescription presc=PrescriptionMapper.PRESCRIPTION_MAPPER.toPrescription(p);
        presc.setDiagnosis(getDiagnosisByTitle(diagnosis));
        prescriptionDAO.addPresc(presc);

    }

	@Override
	@Transactional
	public void addPatient(String surname, String name, String patronymic, String insuranceNum, String doctor){
		PatientDto p=new PatientDto();
		p.setSurname(surname);
		p.setName(name);
		p.setPatronymic(patronymic);
		p.setInsuranceNum(insuranceNum);
		Staff staff=StaffMapper.STAFF_MAPPER.toStaff(getDoctorBySurname(doctor));
		p.setStaff(staff);
		p.setIsDeleted(false);
		p.setIsDischarged(false);

		patientDAO.addPatient(PatientMapper.PATIENT_MAPPER.toPatient(p));
	}

    @Override
    @Transactional
    public void addDiagnosis(String title, String diagnosisType){
        Diagnosis diagnosis=new Diagnosis();
        diagnosis.setTitle(title);
        diagnosis.setDiagnosisType(getDiagnosisTypeByTitle(diagnosisType));
        patientDAO.addDiagnosis(diagnosis);
    }

    @Override
    @Transactional
    public void updatePatient(int id, String surname, String name, String patronymic, String insuranceNum, String doctor) {
	    PatientDto patDto=getById(id);
		patDto.setSurname(surname);
		patDto.setName(name);
		patDto.setPatronymic(patronymic);
		patDto.setInsuranceNum(insuranceNum);
        Staff staff=StaffMapper.STAFF_MAPPER.toStaff(getDoctorBySurname(doctor));
		patDto.setStaff(staff);
		patDto.setIsDeleted(false);
		patDto.setIsDischarged(false);

        this.patientDAO.updatePatient(PatientMapper.PATIENT_MAPPER.toPatient(patDto));
    }
}
