package hospital.dto.mappers;

import hospital.dto.PatientDto;
import hospital.dto.ProcedureDto;
import hospital.dto.ProcedureTitleDto;
import hospital.model.Patient;
import hospital.model.Procedure;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcedureMapper {
    ProcedureMapper PROCEDURE_MAPPER = Mappers.getMapper(ProcedureMapper.class);
    ProcedureTitleDto fromProcedure(Procedure procedure);
    @InheritInverseConfiguration
    Procedure toProcedure(ProcedureTitleDto procedure);
}
