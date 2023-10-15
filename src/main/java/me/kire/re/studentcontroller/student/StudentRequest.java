package me.kire.re.studentcontroller.student;

import lombok.Builder;

@Builder
public record StudentRequest(
        String id,
        String name
) {
}
