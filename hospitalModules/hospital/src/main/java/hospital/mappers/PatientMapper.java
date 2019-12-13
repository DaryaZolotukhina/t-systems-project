package hospital.mappers;

import hospital.dto.event.EventAjax;
import hospital.dto.event.EventUIDto;
import hospital.dto.patient.CreatePatientRequest;
import hospital.dto.patient.PatientDto;
import hospital.dto.patient.UpdatePatientRequest;
import hospital.model.Event;
import hospital.model.Patient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
        PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);
        PatientDto fromPatient(Patient patient);
        @InheritInverseConfiguration
        Patient toPatient(PatientDto patientDto);
        Patient toPatientfromCreatePatientRequest(CreatePatientRequest createPatientRequest);
        Patient toPatientfromUpdatePatientRequest(UpdatePatientRequest updatePatientRequest);

    }
