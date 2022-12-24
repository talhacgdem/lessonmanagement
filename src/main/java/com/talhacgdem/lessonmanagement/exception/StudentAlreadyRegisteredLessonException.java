package com.talhacgdem.lessonmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class StudentAlreadyRegisteredLessonException extends RuntimeException{
    public StudentAlreadyRegisteredLessonException(Long id){
        super("Student already registered to lesson from given id : " + id);
    }
}
