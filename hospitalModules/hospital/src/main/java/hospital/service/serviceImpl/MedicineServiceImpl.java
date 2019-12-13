package hospital.service.serviceImpl;

import hospital.dao.MedicineDAO;
import hospital.dto.medicine.MedicineTitleDto;
import hospital.mappers.MedicineMapper;
import hospital.model.DiagnosisType;
import hospital.model.Medicine;
import hospital.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineDAO medicineDAO;
    @Autowired
    public MedicineServiceImpl(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    @Override
    @Transactional
    public Medicine getMedicineByTitle(String title) {
        Medicine medicine=medicineDAO.getMedicineByTitle(title);
        return medicine;
    }

    @Transactional
    @Override
    public List<MedicineTitleDto> getAllMedicineForDiagnosis(String titleDiag) {
        List<Medicine> listMedicine = medicineDAO.getAllMedicine();
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
            listMedicineTitleDto.add(MedicineMapper.MEDICINE_MAPPER.fromMedicine(med));
        }
        return listMedicineTitleDto;
    }
}
