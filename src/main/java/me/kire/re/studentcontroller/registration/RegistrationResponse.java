package me.kire.re.studentcontroller.registration;

import lombok.Builder;
import me.kire.re.studentcontroller.util.RegistrationType;

@Builder
public record RegistrationResponse(
        String id,
        RegistrationType type,
        String studentId
) {
}
