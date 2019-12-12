package hospital.dao;

import hospital.model.Staff;

import java.util.List;

public interface DoctorDAO {
    public List<Staff> getAllDoctors();
    public Staff getDoctorBySurname(String surname);
    Staff getById(int id);
}
