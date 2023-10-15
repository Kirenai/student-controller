package me.kire.re.studentcontroller.registration;

import lombok.Builder;

@Builder
public record RegistrationResponse(
        String id,
        String type,
        String studentId
) {
}
