package com.sysmap.mslearningcourse.resources;

import com.sysmap.mslearningcourse.domain.dtos.CourseInsertDTO;
import com.sysmap.mslearningcourse.domain.dtos.CourseResponseDTO;
import com.sysmap.mslearningcourse.domain.entities.Course;
import com.sysmap.mslearningcourse.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> insertCourse(@RequestBody CourseInsertDTO courseInsertDTO){
        CourseResponseDTO courseResponseDTO = courseService.insertCourse(courseInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(courseResponseDTO.getCourseId()).toUri();
        return ResponseEntity.created(uri).body(courseResponseDTO);
    }

    // Retorna um findAll se não encontrar o Id
    @GetMapping(value = "/{courseId}")
    public ResponseEntity<?> findByCourseOrFindAll(@PathVariable UUID courseId) {
        return ResponseEntity.ok((courseService.findByCourseId(courseId) != null) ? courseService.findByCourseId(courseId) : courseService.findAllCourses());
    }

    @GetMapping(value = "/id/{courseId}")
    public ResponseEntity<Course> findByCourseId(@PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.findByIdEntity(courseId));
    }
}
