package com.talhacgdem.lessonmanagement.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentUpdateDto {
    private Long id;
    private String name;
    private String surname;
}
