package com.sysmap.domain.dtos;

import com.sysmap.domain.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String fullName;
    private String document;
    private LocalDate birthdate;
    private String courseName;
    private boolean status;

    public StudentDTO(Student entity) {
        fullName = entity.getFirstName() + " " + entity.getLastName();
        document = entity.getDocument();
        birthdate = entity.getBirthdate();
        courseName = courseName;
        status = entity.isStatus();
    }
}
