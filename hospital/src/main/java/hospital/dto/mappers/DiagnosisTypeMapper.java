package hospital.dto.mappers;

import hospital.dto.DiagnosisTypeDto;
import hospital.dto.DiagnosisTypeTitleDto;
import hospital.model.DiagnosisType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiagnosisTypeMapper {
    DiagnosisTypeMapper DIAGNOSIS_TYPE_MAPPER = Mappers.getMapper(DiagnosisTypeMapper.class);
    DiagnosisTypeDto fromDiagnosisType(DiagnosisType diagnosisType);
    @InheritInverseConfiguration
    DiagnosisType toDiagnosisType(DiagnosisTypeDto diagnosisTypeDto);

    DiagnosisTypeTitleDto fromDiagnosisTypeTitle(DiagnosisType diagnosisType);
    @InheritInverseConfiguration
    DiagnosisType toDiagnosisTypeTitle(DiagnosisTypeTitleDto diagnosisTypeTitleDto);
   /* @Mapping(target = "")
    DiagnosisTypeTitleDto fromDiagnosisTypeToTitle(DiagnosisType diagnosisType);
    @InheritInverseConfiguration
    DiagnosisType toDiagnosisType(DiagnosisTypeTitleDto diagnosisTypeTitleDto);*/
}
