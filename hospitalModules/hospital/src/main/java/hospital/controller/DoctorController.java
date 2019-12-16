package hospital.controller;

import hospital.dto.StaffDto;
import hospital.service.DoctorService;
import hospital.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired(required=true)
    @Qualifier(value="doctorService")
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService= doctorService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<StaffDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @RequestMapping(value = "/procedure/{procedure}", method = RequestMethod.GET)
    @ResponseBody
    public List<StaffDto> getDoctorsForProcedure(@PathVariable("procedure") String procedure)
    {
        return doctorService.getDoctorsForProcedure(procedure);
    }
}
