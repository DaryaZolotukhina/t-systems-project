package hospital.service.serviceImpl;

import hospital.dao.DiagnosisDAO;
import hospital.dao.PatientDAO;
import hospital.dto.diagnosis.DiagnosisTypeTitleDto;
import hospital.dto.patient.PatientDto;
import hospital.model.Diagnosis;
import hospital.model.DiagnosisType;
import hospital.model.Patient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class DiagnosisServiceImplTest {

    private DiagnosisServiceImpl sut;
    private DiagnosisDAO diagnosisDAO;
    private Diagnosis diagnosis;
    private DiagnosisType diagnosisType;


    @Before
    public void setUp(){
        diagnosisDAO= Mockito.mock(DiagnosisDAO.class);
        sut=new DiagnosisServiceImpl(diagnosisDAO);

    }

    @Test
    public void testGetDiagnosisByTitle_flu_returnEntityFlu(){
        diagnosis = new Diagnosis();
        diagnosis.setTitle("flu");
        diagnosis.setId(1);

        // when
        Mockito.when(diagnosisDAO.getDiagnosisByTitle("flu")).thenReturn(diagnosis);
        Diagnosis result = sut.getDiagnosisByTitle("flu");

        // then
        Assert.assertEquals(diagnosis, result);
    }

    @Test
    public void testGetDiagnosisTypeByTitle_flu_returnEntityTypeFlu(){
        diagnosisType = new DiagnosisType();
        diagnosisType.setTitle("flu");
        diagnosisType.setId(1);

        // when
        Mockito.when(diagnosisDAO.getDiagnosisTypeByTitle("flu")).thenReturn(diagnosisType);
        DiagnosisType result = sut.getDiagnosisTypeByTitle("flu");

        // then
        Assert.assertEquals(diagnosisType, result);
    }

    @Test
    public void testGetAllDiagnosisType(){
        List<DiagnosisTypeTitleDto> diagnosisTypeTitleDtoList=sut.getAllDiagnosisType();

        Mockito.verify(diagnosisDAO, Mockito.times(1)).getAllDiagnosisType();
    }

}