package com.sysmap.mslearningcourse.domain.dtos;

import com.sysmap.mslearningcourse.domain.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {

    private UUID courseId;

    public CourseResponseDTO(Course entity) {
        courseId = entity.getCourseId();
    }
}
