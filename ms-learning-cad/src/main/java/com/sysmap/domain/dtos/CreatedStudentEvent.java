package com.sysmap.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedStudentEvent {
    private UUID studentId;
    private String fullName;
    private UUID courseId;

}
