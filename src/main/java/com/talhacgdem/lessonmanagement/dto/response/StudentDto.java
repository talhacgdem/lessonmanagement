package com.talhacgdem.lessonmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
    private String name;
    private String surname;
}
