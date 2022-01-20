package com.sysmap.mslearningcourse.data.repositories;

import com.sysmap.mslearningcourse.domain.entities.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends MongoRepository<Course, UUID> {

    Optional<Course> findByCourseId(UUID courseId);
}
