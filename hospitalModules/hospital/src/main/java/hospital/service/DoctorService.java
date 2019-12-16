package hospital.service;

import hospital.dto.StaffDto;

import java.math.BigInteger;
import java.util.List;

public interface DoctorService {

    StaffDto getDoctorBySurname(String surname);

    List<StaffDto> getAllDoctors();

    List<StaffDto> getDoctorsForProcedure(String procedureTitle);

    StaffDto getById(BigInteger id);
}
