package com.sysmap.data.repositories;

import com.sysmap.domain.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends MongoRepository<Student, UUID> {

    Optional<Student> findByStudentId(UUID studentId);
}
