package me.kire.re.studentcontroller.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static me.kire.re.studentcontroller.util.Constants.REGISTRATION_ENDPOINT;

@Configuration
public class RegistrationRouter {

    @Bean
    public RouterFunction<ServerResponse> registrationRouterFunction(RegistrationHandler handler) {
        return RouterFunctions.route()
                .GET(REGISTRATION_ENDPOINT + "/search/student/{studentId}", handler::findByStudentId)
                .POST(REGISTRATION_ENDPOINT + "/student/{studentId}", handler::create)
                .build();
    }

}
