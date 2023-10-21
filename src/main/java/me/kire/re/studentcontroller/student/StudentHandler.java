package me.kire.re.studentcontroller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<StudentResponse> response = this.studentRepository.findAll()
                .map(this.studentMapper::mapOut);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(response, StudentResponse.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(StudentRequest.class)
                .map(this.studentMapper::mapIn)
                .flatMap(this.studentRepository::save)
                .flatMap(student -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .bodyValue(student)
                );
    }

}
