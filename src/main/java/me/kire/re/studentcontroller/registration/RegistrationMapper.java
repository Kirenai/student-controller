package me.kire.re.studentcontroller.registration;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration mapIn(RegistrationRequest request);

    RegistrationResponse mapOut(Registration registration);

}
