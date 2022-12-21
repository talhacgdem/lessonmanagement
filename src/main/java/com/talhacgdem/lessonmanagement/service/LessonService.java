package com.talhacgdem.lessonmanagement.service;

import com.talhacgdem.lessonmanagement.dto.request.LessonCreateDto;
import com.talhacgdem.lessonmanagement.dto.response.LessonResponseDto;
import com.talhacgdem.lessonmanagement.entity.Lesson;
import com.talhacgdem.lessonmanagement.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    public LessonResponseDto newLesson(LessonCreateDto lessonCreateDto) {
        Lesson l = lessonRepository.save(
                modelMapper.map(lessonCreateDto, Lesson.class)
        );
        return modelMapper.map(l, LessonResponseDto.class);
    }
}
