package hospital.dao;

import hospital.model.Medicine;

import java.util.List;

public interface MedicineDAO {

    Medicine getMedicineByTitle(String title);

    List<Medicine> getAllMedicine();
}
