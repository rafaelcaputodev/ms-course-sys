package com.sysmap.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sysmap.domain.dtos.StudentDTO;
import com.sysmap.domain.dtos.StudentInsertDTO;
import com.sysmap.domain.dtos.StudentResponseDTO;
import com.sysmap.domain.entities.Student;
import com.sysmap.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> insertStudent(@RequestBody StudentInsertDTO studentInsertDTO) throws JsonProcessingException {
        StudentResponseDTO studentResponseDTO = studentService.insertStudent(studentInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentInsertDTO.getCourseId()).toUri();
        return ResponseEntity.created(uri).body(studentResponseDTO);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentDTO> findByStudent(@PathVariable UUID studentId) {
        StudentDTO dto = studentService.findByStudentId(studentId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }
}
