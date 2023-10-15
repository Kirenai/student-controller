package me.kire.re.studentcontroller.registration;

import lombok.RequiredArgsConstructor;
import me.kire.re.studentcontroller.student.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static me.kire.re.studentcontroller.util.Constants.STUDENT_ID_PATH;
import static me.kire.re.studentcontroller.util.FunctionUtils.pathVariableBy;
import static org.springframework.http.HttpStatus.CREATED;

@Component
@RequiredArgsConstructor
public class RegistrationHandler {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final RegistrationMapper registrationMapper;

    public Mono<ServerResponse> findByStudentId(ServerRequest request) {
        String studentId = pathVariableBy(request, STUDENT_ID_PATH);
        Flux<RegistrationResponse> registrations = this.registrationRepository.findByStudentId(studentId)
                .map(this.registrationMapper::mapOut);
        return ServerResponse.ok().body(registrations, RegistrationResponse.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        String studentId = pathVariableBy(request, STUDENT_ID_PATH);
        return this.studentRepository.findById(studentId)
                .flatMap(student ->
                        request.bodyToMono(RegistrationRequest.class)
                                .map(this.registrationMapper::mapIn)
                                .map(registration -> {
                                    registration.setStudentId(student.getId());
                                    return registration;
                                }))
                .flatMap(this.registrationRepository::save)
                .flatMap(student -> ServerResponse.status(CREATED).bodyValue(student));
    }

}
