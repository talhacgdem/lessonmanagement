package com.talhacgdem.lessonmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(Long id){
        super("Lesson not found from given id : " + id);
    }
}
