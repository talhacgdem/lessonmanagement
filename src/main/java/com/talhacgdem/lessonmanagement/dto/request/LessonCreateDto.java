package com.talhacgdem.lessonmanagement.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonCreateDto {
    private String name;
    private Integer quota;
}
