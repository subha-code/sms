package com.student.controller;


import com.student.entity.Student;
import com.student.payload.RegDto;
import com.student.payload.StudentDto;
import com.student.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/studentreg")

///?id=
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @PostMapping
//    public ResponseEntity<Student> addStudent(@RequestBody Student student){
//
//        Student serviceStudent = studentService.createStudent(student);
//        // serviceStudent stores saved data which is return by service layer
//        return new ResponseEntity<>(serviceStudent, HttpStatus.CREATED);
//
//    }

    //@RequestMapping(value = "/items", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> addStudent( @Valid @RequestBody StudentDto studentDto,
                                                  BindingResult result){


        // serviceStudent stores saved data which is return by service layer
        if(result.hasErrors())  {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }

        StudentDto Dto= studentService.createStudent(studentDto);
        return new ResponseEntity<>(Dto,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam long id){
        studentService.deleteRegistrationById(id);
        return new ResponseEntity<>("Registration deleted", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<StudentDto> updateRegistration(@RequestParam long id,
                 @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.updateRegistration(id, studentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/studentreg?pageNo=1&pageSize=5&sortBy=email&sortDir=asc
    @GetMapping
    public ResponseEntity<RegDto> getAllRegistrationDto(

            @RequestParam(name="pageNo", defaultValue="0", required=false) int pageNo,
            @RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
            @RequestParam(name="sortBy", defaultValue="name", required=false) String sortBy,
            @RequestParam(name="sortDir", defaultValue="name", required=false) String sortDir
    ){
        RegDto allRegistration = studentService.getAllRegistration(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allRegistration, HttpStatus.OK);
    }

@GetMapping("/byId")
public ResponseEntity<StudentDto> getRegistrationById(@RequestParam long id){

    StudentDto registrationById = studentService.getRegistrationById(id);
    return new ResponseEntity<>(registrationById, HttpStatus.OK);
}

}
