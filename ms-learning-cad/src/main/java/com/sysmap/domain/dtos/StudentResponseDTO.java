package com.sysmap.domain.dtos;

import com.sysmap.domain.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private UUID studentId;

    public StudentResponseDTO(Student entity) {
        studentId = entity.getStudentId();
    }
}
