package com.talhacgdem.lessonmanagement.controller;

import com.talhacgdem.lessonmanagement.dto.request.StudentCreateDto;
import com.talhacgdem.lessonmanagement.dto.request.StudentLessonListDto;
import com.talhacgdem.lessonmanagement.dto.request.StudentUpdateDto;
import com.talhacgdem.lessonmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PutMapping("add")
    public ResponseEntity<?> add(@RequestBody StudentCreateDto studentCreateDto){
        return new ResponseEntity<>(studentService.add(studentCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody StudentUpdateDto studentUpdateDto){
        return new ResponseEntity<>(studentService.update(studentUpdateDto), HttpStatus.CREATED);
    }

    @PostMapping("list")
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(studentService.list(), HttpStatus.OK);
    }

    @PostMapping("lessonList")
    public ResponseEntity<?> lessonList(@RequestBody StudentLessonListDto studentLessonListDto){
        return new ResponseEntity<>(studentService.lessonList(studentLessonListDto), HttpStatus.OK);
    }
}
