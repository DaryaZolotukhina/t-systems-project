package hospital.mappers;

import hospital.dto.PatientDto;
import hospital.model.Patient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
        PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);
        PatientDto fromPatient(Patient patient);
        @InheritInverseConfiguration
        Patient toPatient(PatientDto patientDto);
    }
