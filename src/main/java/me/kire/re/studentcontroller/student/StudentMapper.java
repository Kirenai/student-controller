package me.kire.re.studentcontroller.student;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student mapIn(StudentRequest studentRequest);

    StudentResponse mapOut(Student student);

}
