package hospital.dao;

import hospital.model.Staff;

import java.math.BigInteger;
import java.util.List;

public interface DoctorDAO {
    public List<Staff> getAllDoctors();
    public Staff getDoctorBySurname(String surname);
    Staff getById(BigInteger id);
}
