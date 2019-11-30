package hospital.dto.mappers;

import hospital.dto.MedicineDto;
import hospital.dto.MedicineTitleDto;
import hospital.model.Event;
import hospital.model.Medicine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicineMapper {
    MedicineMapper MEDICINE_MAPPER = Mappers.getMapper(MedicineMapper.class);
    MedicineTitleDto fromMedicine(Medicine medicine);
    @InheritInverseConfiguration
    Medicine toMedicine(MedicineTitleDto medicineTitleDto);
}
