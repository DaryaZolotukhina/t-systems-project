package hospital.service.serviceImpl;

import hospital.dao.DoctorDAO;
import hospital.dao.PatientDAO;
import hospital.dto.StaffDto;
import hospital.dto.diagnosis.DiagnosisTypeTitleDto;
import hospital.dto.patient.PatientDto;
import hospital.model.Patient;
import hospital.model.Staff;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class DoctorServiceImplTest {

    private DoctorServiceImpl sut;
    private DoctorDAO doctorDAO;
    private StaffDto staffDto;
    private Staff staff;


    @Before
    public void setUp(){
        doctorDAO= Mockito.mock(DoctorDAO.class);
        sut=new DoctorServiceImpl(doctorDAO);

    }

    @Test
    public void testGetDoctorBySurname_Petriv_returnEntityPetrov() {
        staffDto=new StaffDto();
        staffDto.setId(1);
        staffDto.setSurname("Petrov");

        staff=new Staff();
        staff.setId(1);
        staff.setSurname("Petrov");

        // when
        Mockito.when(doctorDAO.getDoctorBySurname("Petrov")).thenReturn(staff);
        StaffDto result = sut.getDoctorBySurname("Petrov");

        // then
        Assert.assertEquals(staffDto.getId(), result.getId());
    }

    @Test
    public void testGetById_1_returnEntity1() {
        staffDto=new StaffDto();
        staffDto.setId(1);
        staffDto.setSurname("Petrov");

        staff=new Staff();
        staff.setId(1);
        staff.setSurname("Petrov");

        // when
        Mockito.when(doctorDAO.getById(1)).thenReturn(staff);
        StaffDto result = sut.getById(1);

        // then
        Assert.assertEquals(staffDto.getId(), result.getId());
    }

    @Test
    public void testGetAll() {
        List<StaffDto> staffDtoList=sut.getAllDoctors();

        Mockito.verify(doctorDAO, Mockito.times(1)).getAllDoctors();
    }
}