package hospital.service.serviceImpls;

import hospital.dao.PrescriptionDAO;
import hospital.dto.PrescriptionDto;
import hospital.dto.mappers.PrescriptionMapper;
import hospital.model.Prescription;
import hospital.service.PrescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionDAO prescriptionDAO;

    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<PrescriptionDto> getAllPrescriptions(int id){
        List<PrescriptionDto> listPrescriptionDto=new ArrayList<>();
        List<Prescription> listPrescription= prescriptionDAO.getAllPrescriptions(id);
        for (Prescription prescription : listPrescription){
            listPrescriptionDto.add(PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescription(prescription));
        }
        return listPrescriptionDto;
    }

    @Override
    public PrescriptionDto getPrescriptionById(int id){
        Prescription prescription = prescriptionDAO.getPrescriptionById(id);
        return PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescription(prescription);
    }

    @Override
    @Transactional
    public void deletePrescription(Prescription presc){
        prescriptionDAO.deletePrescription(presc);
    }

    @Override
    @Transactional
    public void updateDeletePrescription(Prescription presc) {
        presc.setIsDeleted(true);
        prescriptionDAO.updatePrescription(presc);
    }

}
