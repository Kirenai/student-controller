package me.kire.re.studentcontroller.registration;

import lombok.Builder;
import me.kire.re.studentcontroller.util.RegistrationType;

@Builder
public record RegistrationRequest(
        RegistrationType type
) {
}
