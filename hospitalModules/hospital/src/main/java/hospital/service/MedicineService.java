package hospital.service;

import hospital.dto.medicine.MedicineTitleDto;
import hospital.model.Medicine;

import java.util.List;

public interface MedicineService {

    List<MedicineTitleDto> getAllMedicineForDiagnosis(String titleDiag);

    Medicine getMedicineByTitle(String title);
}
