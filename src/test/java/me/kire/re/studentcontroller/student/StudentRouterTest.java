package me.kire.re.studentcontroller.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static me.kire.re.studentcontroller.util.Constants.STUDENT_ENDPOINT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {StudentRouter.class, StudentHandler.class})
class StudentRouterTest {

    @Autowired
    private WebTestClient client;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentMapper studentMapper;

    @Test
    public void whenGET_findAll() {
        when(this.studentRepository.findAll()).thenReturn(
                Flux.just(new Student("1222222222", "Lucas"), new Student("1333333333", "Marcos"))
        );
        when(this.studentMapper.mapOut(any())).thenReturn(
                new StudentResponse("1222222222", "Lucas"),
                new StudentResponse("1333333333", "Marcos")
        );

        this.client
                .get()
                .uri(STUDENT_ENDPOINT)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(StudentResponse.class)
                .hasSize(2);
    }

    @Test
    public void whenPOST_create() {
        when(this.studentMapper.mapIn(any())).thenReturn(new Student("1222222222", "Lucas"));
        when(this.studentRepository.save(any())).thenReturn(Mono.just(new Student("1222222222", "Lucas")));

        this.client
                .post()
                .uri(STUDENT_ENDPOINT)
                .body(Mono.just(new StudentRequest("Lucas")), StudentRequest.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

}