package hospital.controller;

import hospital.dto.diagnosis.DiagnosisTypeTitleDto;
import hospital.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    @ResponseBody
    public List<DiagnosisTypeTitleDto> getAllDiagnosisType() {
        return diagnosisService.getAllDiagnosisType();
    }

}
