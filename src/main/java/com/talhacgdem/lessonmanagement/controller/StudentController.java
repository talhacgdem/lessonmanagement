package com.talhacgdem.lessonmanagement.controller;

import com.talhacgdem.lessonmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
}
