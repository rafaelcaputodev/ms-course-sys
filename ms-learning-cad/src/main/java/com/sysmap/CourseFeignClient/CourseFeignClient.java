package com.sysmap.CourseFeignClient;

import com.sysmap.domain.entities.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name = "ms-learning-course", url = "localhost:8081", path = "/api/v1/course")
public interface CourseFeignClient {

    @GetMapping(value = "/id/{courseId}")
    ResponseEntity<Course> findById(@PathVariable UUID courseId);
}
