package hospital.controller;

import hospital.dto.procedure.ProcedureTitleDto;
import hospital.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProcedureController {
    private ProcedureService procedureService;

    @Autowired(required=true)
    @Qualifier(value="procedureService")
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService= procedureService;
    }

    @RequestMapping(value = "/allProcedureForDiagnosis/{diagTypeTitle}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcedureTitleDto> getAllProcedureForDiagnosis(@PathVariable("diagTypeTitle") String diagTypeTitle) {
        return procedureService.getAllProcedureForDiagnosis(diagTypeTitle);
    }
}
