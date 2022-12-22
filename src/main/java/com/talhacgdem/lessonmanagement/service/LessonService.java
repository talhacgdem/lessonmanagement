package com.talhacgdem.lessonmanagement.service;

import com.talhacgdem.lessonmanagement.dto.request.LessonCreateDto;
import com.talhacgdem.lessonmanagement.dto.request.LessonStudentListDto;
import com.talhacgdem.lessonmanagement.dto.request.LessonUpdateDto;
import com.talhacgdem.lessonmanagement.dto.response.LessonDto;
import com.talhacgdem.lessonmanagement.dto.response.StudentDto;
import com.talhacgdem.lessonmanagement.entity.Lesson;
import com.talhacgdem.lessonmanagement.exception.LessonNotFoundException;
import com.talhacgdem.lessonmanagement.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    public LessonDto add(LessonCreateDto lessonCreateDto) {
        Lesson l = lessonRepository.save(
                modelMapper.map(lessonCreateDto, Lesson.class)
        );
        return modelMapper.map(l, LessonDto.class);
    }

    public LessonDto update(LessonUpdateDto lessonUpdateDto) {
        Lesson l = lessonRepository.findById(lessonUpdateDto.getId()).orElseThrow(
                () -> new LessonNotFoundException(lessonUpdateDto.getId())
        );
        return modelMapper.map(lessonRepository.save(l), LessonDto.class);
    }

    public List<LessonDto> list() {
        return lessonRepository.findAll().stream().map(l -> modelMapper.map(l, LessonDto.class))
                .collect(Collectors.toList());
    }

    public List<StudentDto> studentList(LessonStudentListDto lessonStudentListDto) {
        Lesson lesson = lessonRepository.findById(lessonStudentListDto.getId()).orElseThrow(
                () -> new LessonNotFoundException(lessonStudentListDto.getId())
        );
        return lesson.getStudents().stream().map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
}
