package me.kire.re.studentcontroller.student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class Student {

    @Id
    private String id;
    private String name;

}
