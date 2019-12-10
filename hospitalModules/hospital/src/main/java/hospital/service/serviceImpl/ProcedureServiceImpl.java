package hospital.service.serviceImpl;

import hospital.dao.MedicineDAO;
import hospital.dao.ProcedureDAO;
import hospital.dto.ProcedureTitleDto;
import hospital.mappers.ProcedureMapper;
import hospital.model.DiagnosisType;
import hospital.model.Procedure;
import hospital.service.ProcedureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureDAO procedureDAO;
    public void setProcedureDAO(ProcedureDAO procedureDAO) {
        this.procedureDAO = procedureDAO;
    }

    @Override
    @Transactional
    public Procedure getProcedureByTitle(String title) {
        Procedure procedure=procedureDAO.getProcedureByTitle(title);
        return procedure;
    }

    @Transactional
    @Override
    public List<ProcedureTitleDto> getAllProcedureForDiagnosis(String titleDiag) {
        List<Procedure> listProc = procedureDAO.getAllProcedure();
        List<Procedure> listProcForDiag = new ArrayList<>();
        for (Procedure proc : listProc) {
            for (DiagnosisType diagnosisType : proc.getDiagnosisTypes()){
                if (diagnosisType.getTitle().equals(titleDiag)){
                    listProcForDiag.add(proc);
                    continue;
                }
            }
        }
        List<ProcedureTitleDto> listProcTitleDto=new ArrayList<>();
        for (Procedure proc : listProcForDiag){
            listProcTitleDto.add(ProcedureMapper.PROCEDURE_MAPPER.fromProcedure(proc));
        }
        return listProcTitleDto;
    }
}
