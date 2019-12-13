package hospital.service.serviceImpl;

import hospital.dao.PatientDAO;
import hospital.dao.ProcedureDAO;
import hospital.dto.medicine.MedicineTitleDto;
import hospital.dto.patient.PatientDto;
import hospital.dto.procedure.ProcedureTitleDto;
import hospital.model.Diagnosis;
import hospital.model.Patient;
import hospital.model.Procedure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.access.method.P;

import java.util.List;

import static org.junit.Assert.*;

public class ProcedureServiceImplTest {

    private ProcedureServiceImpl sut;
    private ProcedureDAO procedureDAO;
    private Procedure procedure;


    @Before
    public void setUp(){
        procedureDAO= Mockito.mock(ProcedureDAO.class);
        sut=new ProcedureServiceImpl(procedureDAO);

    }
    @Test
    public void testGetProcedureByTitle_injection_returnEntityInjection() {

        procedure=new Procedure();
        procedure.setTitle("injection");
        procedure.setId(1);

        // when
        Mockito.when(procedureDAO.getProcedureByTitle("injection")).thenReturn(procedure);
        Procedure result = sut.getProcedureByTitle("injection");

        // then
        Assert.assertEquals(procedure, result);
    }

    @Test
    public void testGetAll(){
        List<ProcedureTitleDto> procedureTitleDtoList=sut.getAllProcedureForDiagnosis("flu");

        Mockito.verify(procedureDAO, Mockito.times(1)).getAllProcedure();
    }
}