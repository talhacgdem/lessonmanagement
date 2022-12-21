package com.talhacgdem.lessonmanagement.dto.request;

import lombok.Data;

@Data
public class LessonCreateDto {
    private String name;
    private Integer quota;
}
