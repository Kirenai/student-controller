package me.kire.re.studentcontroller.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static me.kire.re.studentcontroller.util.Constants.STUDENT_ENDPOINT;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> router(StudentHandler studentHandler) {
        return RouterFunctions.route()
                .POST(STUDENT_ENDPOINT, studentHandler::create)
                .build();
    }

}
