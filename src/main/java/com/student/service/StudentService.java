package com.student.service;

import com.student.payload.RegDto;
import com.student.payload.StudentDto;

import java.util.List;

public interface StudentService {
    public StudentDto createStudent(StudentDto studentDto);

    void deleteRegistrationById(long id);

   StudentDto updateRegistration(long id, StudentDto studentDto);

    RegDto getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto getRegistrationById(long id);
}
