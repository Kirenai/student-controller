package me.kire.re.studentcontroller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class StudentHandler {
    private final StudentRepository studentRepository;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(this.studentRepository::save)
                .flatMap(student -> ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(student));
    }
}
