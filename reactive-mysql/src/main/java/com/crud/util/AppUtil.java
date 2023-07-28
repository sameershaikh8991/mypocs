package com.crud.util;

import com.crud.dto.StudentDto;
import com.crud.model.Student;
import org.springframework.beans.BeanUtils;

public class AppUtil {
    public  static StudentDto entityToDto(Student student){
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return  studentDto;
    }

    public  static Student DtoToEntity(StudentDto studentDto){
        Student student = new Student();
        BeanUtils.copyProperties(studentDto,student);
        return student;
    }
}
