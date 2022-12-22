package com.talhacgdem.lessonmanagement.dto.request;

import lombok.Data;

@Data
public class StudentUpdateDto {
    private Long id;
    private String name;
    private String surname;
}
