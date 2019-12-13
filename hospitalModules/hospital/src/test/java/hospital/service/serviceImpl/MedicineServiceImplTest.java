package hospital.service.serviceImpl;

import hospital.dao.MedicineDAO;
import hospital.dao.ProcedureDAO;
import hospital.dto.diagnosis.DiagnosisTypeTitleDto;
import hospital.dto.medicine.MedicineTitleDto;
import hospital.model.Medicine;
import hospital.model.Procedure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class MedicineServiceImplTest {

    private MedicineServiceImpl sut;
    private MedicineDAO medicineDAO;
    private Medicine medicine;


    @Before
    public void setUp(){
        medicineDAO= Mockito.mock(MedicineDAO.class);
        sut=new MedicineServiceImpl(medicineDAO);

    }

    @Test
    public void testGetMedicineByTitle_analgin_returnEntityAnalgin() {
        medicine=new Medicine();
        medicine.setTitle("analgin");
        medicine.setId(1);

        // when
        Mockito.when(medicineDAO.getMedicineByTitle("analgin")).thenReturn(medicine);
        Medicine result = sut.getMedicineByTitle("analgin");

        // then
        Assert.assertEquals(medicine, result);
    }

    @Test
    public void testGetAll(){
        List<MedicineTitleDto> medicineTitleDtoList=sut.getAllMedicineForDiagnosis("flu");

        Mockito.verify(medicineDAO, Mockito.times(1)).getAllMedicine();
    }
}