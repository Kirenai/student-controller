package me.kire.re.studentcontroller.student;

import lombok.Builder;

@Builder
public record StudentResponse(
        String id,
        String name
) {
}
