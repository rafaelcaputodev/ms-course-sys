package com.sysmap.domain.dtos;

import com.sysmap.domain.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInsertDTO {

    private String firstName;
    private String lastName;
    private String document;
    private LocalDate birthdate;
    private UUID courseId;
}
