package hospital.dto.mappers;

import hospital.dto.DiagnosisTypeDto;
import hospital.model.DiagnosisType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiagnosisTypeMapper {
    DiagnosisTypeMapper DIAGNOSIS_TYPE_MAPPER = Mappers.getMapper(DiagnosisTypeMapper.class);
    DiagnosisTypeDto fromDiagnosisType(DiagnosisType diagnosisType);
    @InheritInverseConfiguration
    DiagnosisType toDiagnosisType(DiagnosisTypeDto diagnosisTypeDto);
}
