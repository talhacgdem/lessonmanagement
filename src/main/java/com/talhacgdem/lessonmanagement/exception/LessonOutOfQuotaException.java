package com.talhacgdem.lessonmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LessonOutOfQuotaException extends RuntimeException{
    public LessonOutOfQuotaException(Long id){
        super("The student quota of the given id course is full. Lesson id : " + id);
    }
}
