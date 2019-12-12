package hospital.mappers;

import hospital.dto.event.EventAjax;
import hospital.dto.event.EventDto;
import hospital.dto.event.EventUIDto;
import hospital.model.Event;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);
    EventDto fromEvent(Event event);
    @InheritInverseConfiguration
    Event toEvent(EventDto eventDto);
    EventUIDto fromEventUI(Event event);
    @InheritInverseConfiguration
    Event toEventUI(EventUIDto eventDto);
    @Mapping(source = "medicine.title", target = "medicine")
    @Mapping(source = "procedure.title", target = "procedure")
    @Mapping(source = "patient.id", target = "idPatient")
    @Mapping(source = "staff.id", target = "idStaff")
    @Mapping(source = "patient.surname", target = "surnamePatient")
    EventAjax fromEventAjax(Event event);
    @InheritInverseConfiguration
    Event toEventAjax(EventAjax eventDto);
}
