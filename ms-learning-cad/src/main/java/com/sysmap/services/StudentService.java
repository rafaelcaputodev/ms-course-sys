package com.sysmap.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.CourseFeignClient.CourseFeignClient;
import com.sysmap.data.repositories.StudentRepository;
import com.sysmap.domain.dtos.CreatedStudentEvent;
import com.sysmap.domain.dtos.StudentDTO;
import com.sysmap.domain.dtos.StudentInsertDTO;
import com.sysmap.domain.dtos.StudentResponseDTO;
import com.sysmap.domain.entities.Course;
import com.sysmap.domain.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseFeignClient courseFeignClient;
    private final ObjectMapper objectMapper;
    private final EventService eventService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseFeignClient courseFeignClient,
                          ObjectMapper objectMapper, EventService eventService) {
        this.studentRepository = studentRepository;
        this.courseFeignClient = courseFeignClient;
        this.objectMapper = objectMapper;
        this.eventService = eventService;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StudentDTO findByStudentId(UUID studentId) {
        Optional<Student> student = studentRepository.findByStudentId(studentId);
        Course course = courseFeignClient.findById(student.get().getCourseId()).getBody();
        StudentDTO studentDTO = new StudentDTO(student.get());
        studentDTO.setCourseName(course.getCourseName());
        return studentDTO;

    }

    @Transactional
    public StudentResponseDTO insertStudent(StudentInsertDTO studentInsertDTO) throws JsonProcessingException {
            Student student = new Student();
            copyDtoToEntity(student, studentInsertDTO);

            student = studentRepository.insert(student);

            if(student != null){
                CreatedStudentEvent createdStudentEvent = new CreatedStudentEvent();
                createdStudentEvent.setStudentId(student.getStudentId());
                createdStudentEvent.setCourseId(student.getCourseId());
                createdStudentEvent.setFullName(student.getFirstName() + " " + student.getLastName());
                String eventJson = objectMapper.writeValueAsString(createdStudentEvent);
                eventService.send(eventJson);
            }

            return new StudentResponseDTO(student);
    }

    private void copyDtoToEntity(Student student, StudentInsertDTO studentInsertDTO) {
        student.setFirstName(studentInsertDTO.getFirstName());
        student.setLastName(studentInsertDTO.getLastName());
        student.setDocument(studentInsertDTO.getDocument());
        student.setBirthdate(studentInsertDTO.getBirthdate());
        student.setCourseId(studentInsertDTO.getCourseId());
        student.setCreatedOn(Instant.now());
        student.setStatus(true);
        student.setStudentId(UUID.randomUUID());
    }
}
