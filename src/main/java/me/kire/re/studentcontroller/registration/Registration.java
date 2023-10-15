package me.kire.re.studentcontroller.registration;

import lombok.*;
import me.kire.re.studentcontroller.util.RegistrationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "registrations")
public class Registration {

    @Id
    private String id;
    private RegistrationType type;
    private String studentId;

}
