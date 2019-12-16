package hospital.controller;

import hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired(required=true)
    @Qualifier(value="prescriptionService")
    public void setPrescriptionService(PrescriptionService ps){
        prescriptionService = ps;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String createPresc(@PathVariable("id") BigInteger id, Model model) {
        model.addAttribute("id", id);
        return "createPrescription";
    }
}
