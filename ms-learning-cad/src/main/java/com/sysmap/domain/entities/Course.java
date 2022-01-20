package com.sysmap.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private UUID courseId;
    private String courseName;
    private boolean status;
    private Instant createdOn;
}
