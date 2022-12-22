package com.talhacgdem.lessonmanagement.controller;

import com.talhacgdem.lessonmanagement.dto.request.LessonCreateDto;
import com.talhacgdem.lessonmanagement.dto.request.LessonStudentListDto;
import com.talhacgdem.lessonmanagement.dto.request.LessonUpdateDto;
import com.talhacgdem.lessonmanagement.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PutMapping("add")
    public ResponseEntity<?> add(@RequestBody LessonCreateDto lessonCreateDto){
        return new ResponseEntity<>(lessonService.add(lessonCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody LessonUpdateDto lessonUpdateDto){
        return new ResponseEntity<>(lessonService.update(lessonUpdateDto), HttpStatus.CREATED);
    }

    @PostMapping("list")
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(lessonService.list(), HttpStatus.OK);
    }

    @PostMapping("studentList")
    public ResponseEntity<?> studentList(@RequestBody LessonStudentListDto lessonStudentListDto){
        return new ResponseEntity<>(lessonService.studentList(lessonStudentListDto), HttpStatus.OK);
    }
}
