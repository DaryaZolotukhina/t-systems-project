package hospital.service.serviceImpl;

import hospital.dao.MedicineDAO;
import hospital.dao.PrescriptionDAO;
import hospital.dto.patient.PatientDto;
import hospital.dto.prescription.PrescriptionDto;
import hospital.model.Medicine;
import hospital.model.Patient;
import hospital.model.Prescription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PrescriptionServiceImplTest {

    private PrescriptionServiceImpl sut;
    private PrescriptionDAO prescriptionDAO;
    private Prescription prescription;
    private PrescriptionDto prescriptionDto;

    @Before
    public void setUp(){
        prescriptionDAO= Mockito.mock(PrescriptionDAO.class);
        sut=new PrescriptionServiceImpl(prescriptionDAO);

    }

    @Test
    public void testGetAllPrescriptions_returnAllPrescriptions() {
        prescription = new Prescription();
        prescription.setId(1);

        List<Prescription> prescriptionList= Collections.singletonList(prescription);

        // when
        Mockito.when(prescriptionDAO.getAllPrescriptions(1)).thenReturn(prescriptionList);
        List<Prescription> result = sut.getAllPrescriptions(1);

        // then
        Assert.assertEquals(prescriptionList, result);
    }

    @Test
    public void testGetPrescriptionById_1_returnEntity1() {
        prescriptionDto = new PrescriptionDto();
        prescriptionDto.setId(1);

        prescription = new Prescription();
        prescription.setId(1);

        // when
        Mockito.when(prescriptionDAO.getPrescriptionById(1)).thenReturn(prescription);
        PrescriptionDto result = sut.getPrescriptionById(1);

        // then
        Assert.assertEquals(prescriptionDto.getId(), result.getId());
    }
}