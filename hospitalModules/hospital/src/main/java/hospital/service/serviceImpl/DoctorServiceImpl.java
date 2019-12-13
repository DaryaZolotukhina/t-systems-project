package hospital.service.serviceImpl;

import hospital.dao.DiagnosisDAO;
import hospital.dao.DoctorDAO;
import hospital.dao.EventDAO;
import hospital.dao.PatientDAO;
import hospital.dto.StaffDto;
import hospital.mappers.StaffMapper;
import hospital.model.Procedure;
import hospital.model.Staff;
import hospital.service.DoctorService;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO;

    @Autowired
    public DoctorServiceImpl(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    @Transactional
    public StaffDto getDoctorBySurname(String surname) {
        return StaffMapper.STAFF_MAPPER.fromStaff(doctorDAO.getDoctorBySurname(surname));

    }

    @Override
    @Transactional
    public StaffDto getById(int id) {
        Staff staff = doctorDAO.getById(id);
        return StaffMapper.STAFF_MAPPER.fromStaff(staff);
    }

    @Transactional
    @Override
    public List<StaffDto> getAllDoctors(){
        List<Staff> staffList=doctorDAO.getAllDoctors();
        List<StaffDto> listStaffDto=new ArrayList<>();
        for (Staff staff : staffList){
            if (staff.getStaffType().getId()==1)
                listStaffDto.add(StaffMapper.STAFF_MAPPER.fromStaff(staff));
        }
        return listStaffDto;
    }

    @Transactional
    @Override
    public List<StaffDto> getDoctorsForProcedure(String procedureTitle){
        List<Staff> staffList=doctorDAO.getAllDoctors();
        List<Staff> staffForProcedureList=new ArrayList<>();
        List<StaffDto> listStaffDto=new ArrayList<>();
        for (Staff staff : staffList) {
            for (Procedure procedure : staff.getSpecialization().getProcedures()) {
                if (procedure.getTitle().equals(procedureTitle)) {
                    staffForProcedureList.add(staff);
                    continue;
                }
            }
        }
        for (Staff staff : staffForProcedureList) {
            listStaffDto.add(StaffMapper.STAFF_MAPPER.fromStaff(staff));
        }
        return listStaffDto;
    }
}
