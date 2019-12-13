package hospital.service.serviceImpl;

import hospital.dao.PatientDAO;
import hospital.dto.patient.PatientDto;
import hospital.model.Patient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PatientServiceImplTest {

    private PatientServiceImpl sut;
    private PatientDAO patientDAO;
    private PatientDto patientDto;
    private Patient patient;


   @Before
    public void setUp(){
        patientDAO= Mockito.mock(PatientDAO.class);
        sut=new PatientServiceImpl(patientDAO);

    }

    @Test
    public void testGetById() {

        PatientDto patientDto=sut.getById(2);

        Mockito.verify(patientDAO, Mockito.times(1)).getById(2);

    }

    @Test
    public void testGetAll() {
        List<PatientDto> listPatientDto=sut.getAll();

        Mockito.verify(patientDAO, Mockito.times(1)).getAll();
    }

    @Test
    public void testGetAll_returnAllPatients() {
        patientDto = new PatientDto();
        patientDto.setName("Petr");
        patientDto.setId(1);

        patient = new Patient();
        patient.setName("Petr");
        patient.setId(1);

        List<PatientDto> patientDtoList= Collections.singletonList(patientDto);
        List<Patient> patientList= Collections.singletonList(patient);

        // when
        Mockito.when(patientDAO.getAll()).thenReturn(patientList);
        List<PatientDto> result = sut.getAll();

        // then
        Assert.assertEquals(patientDto.getId(), result.get(0).getId());
    }

    @Test
    public void testGetByIdTest_1_returnEntity1(){
        patientDto = new PatientDto();
        patientDto.setName("Petr");
        patientDto.setId(1);

        patient = new Patient();
        patient.setName("Petr");
        patient.setId(1);

        // when
        Mockito.when(patientDAO.getById(1)).thenReturn(patient);
        PatientDto result = sut.getById(1);

        // then
        Assert.assertEquals(patientDto.getId(), result.getId());
    }
}
