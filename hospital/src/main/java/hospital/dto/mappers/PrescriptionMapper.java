package hospital.dto.mappers;

import hospital.dto.EventDto;
import hospital.dto.PrescriptionDto;
import hospital.model.Event;
import hospital.model.Prescription;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrescriptionMapper {
    PrescriptionMapper PRESCRIPTION_MAPPER = Mappers.getMapper(PrescriptionMapper.class);
    PrescriptionDto fromPrescription(Prescription prescription);
    @InheritInverseConfiguration
    Prescription toPrescription(PrescriptionDto prescriptionDto);
}
