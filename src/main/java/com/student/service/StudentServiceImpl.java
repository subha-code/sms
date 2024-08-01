package com.student.service;

import com.student.entity.Student;
import com.student.exception.ResourceNotFound;
import com.student.payload.RegDto;
import com.student.payload.StudentDto;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentServiceImpl(){

    }
    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        // call the mapToEntity method and pass as arguments  studentDto and stores the return value of entity object
        Student toEntity = mapToEntity(studentDto);
        Student savedEntity = studentRepository.save(toEntity);
        //save data in the entity class
        StudentDto dto = mapToDto(savedEntity);
        dto.setMessage("Student Registration Added!!"); // customize the response in postman api tesing without touch the entity class
        return dto; // return back to the controller
    }

    @Override
    public void deleteRegistrationById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateRegistration(long id, StudentDto studentDto) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = optional.get();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setMobile(studentDto.getMobile());
        Student saved = studentRepository.save(student);
        StudentDto dto = mapToDto(saved);
        return dto;
    }

    @Override




    public RegDto getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {

         //Ternary operations
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC, sortBy):Sort.by(Sort.Direction.DESC,sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Student> all = studentRepository.findAll(pageable);
        List<Student> registrations = all.getContent();
        List<StudentDto> registrationDtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
//        System.out.println(all.getTotalPages());
//        System.out.println(all.isLast());
//        System.out.println(all.isFirst());
//        System.out.println(pageable.getPageSize());
//        System.out.println(pageable.getPageNumber());


RegDto reg = new RegDto();
reg.setDto(registrationDtos);

//reg.setDto(studentDto.setTotalPages(all.getTotalPages()), studentDto.setLast(all.isLast()), studentDto.setFirst(all.isFirst()), studentDto.setPageSize(pageable.getPageSize()), studentDto.setPageNumber(pageable.getPageNumber()));
//
//reg.setTotalPages(all.getTotalPages());
//reg.setLast(all.isLast());
//reg.setFirst(all.isFirst());
//reg.setPageSize(pageable.getPageSize());
//reg.setPageNumber(pageable.getPageNumber());


return reg;
    }

    @Override
    public StudentDto getRegistrationById(long id) {
        Student student1 = studentRepository.findById(id).orElseThrow(

                () -> new ResourceNotFound("Registration not found with id: " + id));
 //SUPPLIER use in the orElseThrow

        StudentDto dto = mapToDto(student1);
        return dto;
    }

//    @Override
//    public List<StudentDto> getAllRegistration(int pageNo, int pageSize) {
////        StudentServiceImpl r = new StudentServiceImpl();
//
//        //List<Student> registrations = studentRepository.findAll();
//
//    }

    Student mapToEntity(StudentDto dto){ // data goes to entity class
        // StudentDto copy to Student entity
        //using set attribute stores the dto data to entity
        Student entity = new Student();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }

    //Convert entity to dto
    StudentDto mapToDto(Student student){ // data goes to entity class
        // StudentDto copy to Student entity
        //using set attribute stores the dto data to entity
        StudentDto dto = new  StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setMobile(student.getMobile());
        return dto;
    }
}
