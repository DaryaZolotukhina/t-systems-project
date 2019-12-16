package hospital.controller;

import hospital.dto.medicine.MedicineTitleDto;
import hospital.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired(required=true)
    @Qualifier(value="medicineService")
    public void setMedicineService(MedicineService medicineService) {
        this.medicineService= medicineService;
    }

    @RequestMapping(value = "/diagnosis/{diagTypeTitle}", method = RequestMethod.GET)
    @ResponseBody
    public List<MedicineTitleDto> getAllMedicineForDiagnosis(@PathVariable("diagTypeTitle") String diagTypeTitle) {
        return medicineService.getAllMedicineForDiagnosis(diagTypeTitle);
    }
}
