package com.talhacgdem.lessonmanagement.controller;

import com.talhacgdem.lessonmanagement.dto.request.LessonCreateDto;
import com.talhacgdem.lessonmanagement.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PutMapping("new")
    public ResponseEntity<?> newLesson(@RequestBody LessonCreateDto lessonCreateDto){
        return new ResponseEntity<>(lessonService.newLesson(lessonCreateDto), HttpStatus.CREATED);
    }
}
