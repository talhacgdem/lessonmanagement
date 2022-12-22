package com.talhacgdem.lessonmanagement.dto.request;

import lombok.Data;

@Data
public class LessonUpdateDto {
    private Long id;
    private String name;
    private Integer quota;
}
