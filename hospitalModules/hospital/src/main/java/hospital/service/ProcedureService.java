package hospital.service;

import hospital.dto.procedure.ProcedureTitleDto;
import hospital.model.Procedure;

import java.util.List;

public interface ProcedureService {

    List<ProcedureTitleDto> getAllProcedureForDiagnosis(String titleDiag);

    Procedure getProcedureByTitle(String title);
}
