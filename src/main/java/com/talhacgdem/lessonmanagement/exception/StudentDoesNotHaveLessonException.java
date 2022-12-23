package com.talhacgdem.lessonmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentDoesNotHaveLessonException extends RuntimeException{
    public StudentDoesNotHaveLessonException(Long id){
        super("Student does not have lesson from given id : " + id);
    }
}
