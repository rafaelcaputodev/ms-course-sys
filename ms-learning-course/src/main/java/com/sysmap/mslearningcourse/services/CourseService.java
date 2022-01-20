package com.sysmap.mslearningcourse.services;

import com.sysmap.mslearningcourse.data.repositories.CourseRepository;
import com.sysmap.mslearningcourse.domain.dtos.CourseInsertDTO;
import com.sysmap.mslearningcourse.domain.dtos.CourseResponseDTO;
import com.sysmap.mslearningcourse.domain.dtos.CourseViewDTO;
import com.sysmap.mslearningcourse.domain.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public CourseResponseDTO insertCourse(CourseInsertDTO courseInsertDTO){
        Course course = new Course();
        copyDtoToEntity(course, courseInsertDTO);

        course = courseRepository.insert(course);

        return new CourseResponseDTO(course);
    }

    @Transactional(readOnly = true)
    public CourseViewDTO findByCourseId(UUID courseId){
        Optional<Course> course = courseRepository.findByCourseId(courseId);
        return course.map(CourseViewDTO::new).orElse(null);
    }

    @Transactional(readOnly = true)
    public Course findByIdEntity(UUID courseId){
        Optional<Course> course = courseRepository.findByCourseId(courseId);
        return course.get();
    }

    @Transactional(readOnly = true)
    public List<CourseViewDTO> findAllCourses(){
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(x -> new CourseViewDTO(x)).collect(Collectors.toList());
    }

    public void copyDtoToEntity(Course course, CourseInsertDTO dto){
        course.setCourseName(dto.getCourseName());
        course.setCourseId(UUID.randomUUID());
        course.setStatus(true);
        course.setCreatedOn(Instant.now());
    }

}
