package hospital.service;

import hospital.dto.StaffDto;

import java.util.List;

public interface DoctorService {

    StaffDto getDoctorBySurname(String surname);

    List<StaffDto> getAllDoctors();

    List<StaffDto> getDoctorsForProcedure(String procedureTitle);
}
