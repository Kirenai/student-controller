package me.kire.re.studentcontroller.registration;

import me.kire.re.studentcontroller.util.RegistrationType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistrationMapperTest {

    private final RegistrationMapper mapper = Mappers.getMapper(RegistrationMapper.class);

    @Test
    public void when_mapIn() {
        RegistrationRequest registrationRequest = new RegistrationRequest(RegistrationType.IN);
        Registration registration = this.mapper.mapIn(registrationRequest);

        assertNotNull(registration);
        assertNotNull(registration.getType());

        assertEquals(registrationRequest.type(), registration.getType());
    }

    @Test
    public void when_mapOut() {
        Registration registration = new Registration("2111111111", RegistrationType.IN, "1222222222");
        RegistrationResponse registrationResponse = this.mapper.mapOut(registration);

        assertNotNull(registrationResponse);
        assertNotNull(registrationResponse.id());
        assertNotNull(registrationResponse.type());
        assertNotNull(registrationResponse.studentId());

        assertEquals(registration.getId(), registrationResponse.id());
        assertEquals(registration.getType(), registrationResponse.type());
        assertEquals(registration.getStudentId(), registrationResponse.studentId());
    }

}