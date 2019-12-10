package hospital.dao;

import hospital.model.Diagnosis;
import hospital.model.DiagnosisType;

import java.util.List;

public interface DiagnosisDAO {
    List<DiagnosisType> getAllDiagnosisType();

    DiagnosisType getDiagnosisTypeByTitle(String title);

    void addDiagnosis(Diagnosis d);

    Diagnosis getDiagnosisByTitle(String title);
}
