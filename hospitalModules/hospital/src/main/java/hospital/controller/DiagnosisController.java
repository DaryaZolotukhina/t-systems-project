package hospital.controller;

import hospital.dto.DiagnosisTypeTitleDto;
import hospital.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DiagnosisController {
    private DiagnosisService diagnosisService;

    @Autowired(required=true)
    @Qualifier(value="diagnosisService")
    public void setDiagnosisService(DiagnosisService diagnosisService) {
        this.diagnosisService= diagnosisService;
    }

    @RequestMapping(value = "/allDiagnosisType", method = RequestMethod.GET)
    @ResponseBody
    public List<DiagnosisTypeTitleDto> getAllDiagnosisType() {
        return diagnosisService.getAllDiagnosisType();
    }

}
