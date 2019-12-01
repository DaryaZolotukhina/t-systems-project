package hospital.dto.mappers;

import hospital.dto.EventAjax;
import hospital.dto.EventDto;
import hospital.dto.PatientDto;
import hospital.model.Event;
import hospital.model.Patient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);
    EventDto fromEvent(Event event);
    @InheritInverseConfiguration
    Event toEvent(EventDto eventDto);
}
