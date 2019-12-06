package hospital.mappers;

import hospital.dto.PrescriptionDto;
import hospital.dto.PrescriptionError;
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
    PrescriptionError fromPrescriptionError(Prescription prescription);
    @InheritInverseConfiguration
    Prescription toPrescriptionError(PrescriptionError prescriptionDto);
}
