package hospital.dto.mappers;

import hospital.dto.MedicineDto;
import hospital.model.Medicine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicineMapper {
    MedicineMapper MEDICINE_MAPPER = Mappers.getMapper(MedicineMapper.class);
    MedicineDto fromMedicine(Medicine medicine);
    @InheritInverseConfiguration
    Medicine toMedicine(MedicineDto medicine);
}
