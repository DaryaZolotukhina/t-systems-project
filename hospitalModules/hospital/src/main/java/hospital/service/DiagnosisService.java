package hospital.service;

import hospital.dto.DiagnosisTypeTitleDto;
import hospital.model.Diagnosis;
import hospital.model.DiagnosisType;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosisTypeTitleDto> getAllDiagnosisType();

    DiagnosisType getDiagnosisTypeByTitle(String title);

    void addDiagnosis(String title, String diagnosisType);

    Diagnosis getDiagnosisByTitle(String title);
}
