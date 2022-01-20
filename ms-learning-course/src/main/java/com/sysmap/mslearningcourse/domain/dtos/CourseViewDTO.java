package com.sysmap.mslearningcourse.domain.dtos;

import com.sysmap.mslearningcourse.domain.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseViewDTO {

    private String id;

    private UUID courseId;

    private String courseName;

    private boolean status;

    private Instant createdOn;

    public CourseViewDTO(Course entity) {
        id = entity.getId();
        courseId = entity.getCourseId();
        courseName = entity.getCourseName();
        status = entity.isStatus();
        createdOn = entity.getCreatedOn();
    }
}
