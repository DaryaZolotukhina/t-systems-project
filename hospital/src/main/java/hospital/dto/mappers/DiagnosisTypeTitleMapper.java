package hospital.dto.mappers;

import hospital.dto.DiagnosisTypeDto;
import hospital.dto.DiagnosisTypeTitleDto;
import hospital.model.DiagnosisType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiagnosisTypeTitleMapper {
    DiagnosisTypeTitleMapper DIAGNOSIS_TYPE_TITLE_MAPPER = Mappers.getMapper(DiagnosisTypeTitleMapper.class);
    DiagnosisTypeTitleDto fromDiagnosisType(DiagnosisType diagnosisType);
    @InheritInverseConfiguration
    DiagnosisType toDiagnosisType(DiagnosisTypeTitleDto diagnosisTypeTitleDto);
}
