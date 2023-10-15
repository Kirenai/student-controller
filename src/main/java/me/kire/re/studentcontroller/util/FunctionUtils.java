package me.kire.re.studentcontroller.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionUtils {

    public static String pathVariableBy(ServerRequest request, String pathName) {
        return request.pathVariable(pathName);
    }

}
