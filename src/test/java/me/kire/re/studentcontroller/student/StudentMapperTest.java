package me.kire.re.studentcontroller.student;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentMapperTest {

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    public void when_mapIn() {
        StudentRequest studentRequest = new StudentRequest("Lucas");
        Student student = this.mapper.mapIn(studentRequest);

        assertNotNull(student);
        assertNotNull(student.getName());

        assertEquals(studentRequest.name(), student.getName());
    }

    @Test
    public void when_mapOut() {
        Student student = new Student("1222222222", "Lucas");
        StudentResponse studentResponse = this.mapper.mapOut(student);

        assertNotNull(studentResponse);
        assertNotNull(studentResponse.id());
        assertNotNull(studentResponse.name());


        assertEquals(student.getId(), studentResponse.id());
        assertEquals(student.getName(), studentResponse.name());
    }

}