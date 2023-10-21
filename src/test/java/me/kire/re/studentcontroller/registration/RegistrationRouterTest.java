package me.kire.re.studentcontroller.registration;

import me.kire.re.studentcontroller.student.Student;
import me.kire.re.studentcontroller.student.StudentRepository;
import me.kire.re.studentcontroller.util.RegistrationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static me.kire.re.studentcontroller.util.Constants.REGISTRATION_ENDPOINT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {RegistrationRouter.class, RegistrationHandler.class})
class RegistrationRouterTest {

    @Autowired
    private WebTestClient client;
    @MockBean
    private RegistrationRepository registrationRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private RegistrationMapper registrationMapper;

    @Test
    public void whenGET_findByStudentId() {
        when(this.registrationRepository.findByStudentId(anyString())).thenReturn(
                Flux.just(new Registration("2111111111", RegistrationType.IN, "122222222"))
        );
        when(this.registrationMapper.mapOut(any()))
                .thenReturn(new RegistrationResponse("2111111111", RegistrationType.IN, "122222222"));

        this.client
                .get()
                .uri(REGISTRATION_ENDPOINT + "/search/student/1222222222")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(RegistrationResponse.class)
                .hasSize(1);
    }

    @Test
    public void whenPOST_create() {
        when(this.studentRepository.findById(anyString())).thenReturn(Mono.just(new Student("1222222222", "Lucas")));
        when(this.registrationMapper.mapIn(any())).thenReturn(new Registration(null, RegistrationType.IN, null));
        when(this.registrationRepository.save(any()))
                .thenReturn(Mono.just(new Registration("2111111111", RegistrationType.IN, "122222222")));

        this.client
                .post()
                .uri(REGISTRATION_ENDPOINT + "/student/1222222222")
                .body(Mono.just(new RegistrationRequest(RegistrationType.IN)), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

}