package hospital.service.serviceImpl;

import hospital.dao.DiagnosisDAO;
import hospital.dao.PatientDAO;
import hospital.dto.diagnosis.DiagnosisTypeTitleDto;
import hospital.mappers.DiagnosisTypeMapper;
import hospital.model.Diagnosis;
import hospital.model.DiagnosisType;
import hospital.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisDAO diagnosisDAO;
    /*public void setDiagnosisDAO(DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }*/

    @Autowired
    public DiagnosisServiceImpl(DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    @Override
    @Transactional
    public DiagnosisType getDiagnosisTypeByTitle(String title) {
        DiagnosisType diagnosisType=diagnosisDAO.getDiagnosisTypeByTitle(title);
        return diagnosisType;
    }


    @Override
    @Transactional
    public Diagnosis getDiagnosisByTitle(String title) {
        Diagnosis diagnosis=diagnosisDAO.getDiagnosisByTitle(title);
        return diagnosis;
    }

    @Transactional
    @Override
    public List<DiagnosisTypeTitleDto> getAllDiagnosisType(){
        List<DiagnosisType> listDiagnosisType=diagnosisDAO.getAllDiagnosisType();
        List<DiagnosisTypeTitleDto> listDiagnosisTypeTitleDto=new ArrayList<>();
        for (DiagnosisType diagnosisType : listDiagnosisType){
            listDiagnosisTypeTitleDto.add(DiagnosisTypeMapper.DIAGNOSIS_TYPE_MAPPER.fromDiagnosisTypeTitle(diagnosisType));
        }
        return listDiagnosisTypeTitleDto;
    }

    @Override
    @Transactional
    public void addDiagnosis(String title, String diagnosisType){
        Diagnosis diagnosis=new Diagnosis();
        diagnosis.setTitle(title);
        diagnosis.setDiagnosisType(getDiagnosisTypeByTitle(diagnosisType));
        diagnosisDAO.addDiagnosis(diagnosis);
    }
}
