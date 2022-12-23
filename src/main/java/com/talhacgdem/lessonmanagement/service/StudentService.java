package com.talhacgdem.lessonmanagement.service;

import com.talhacgdem.lessonmanagement.dto.request.StudentCreateDto;
import com.talhacgdem.lessonmanagement.dto.request.StudentLessonDto;
import com.talhacgdem.lessonmanagement.dto.request.StudentLessonListDto;
import com.talhacgdem.lessonmanagement.dto.request.StudentUpdateDto;
import com.talhacgdem.lessonmanagement.dto.response.LessonDto;
import com.talhacgdem.lessonmanagement.dto.response.StudentDto;
import com.talhacgdem.lessonmanagement.entity.Student;
import com.talhacgdem.lessonmanagement.exception.StudentNotFoundException;
import com.talhacgdem.lessonmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final LessonService lessonService;

    public StudentDto add(StudentCreateDto studentCreateDto) {
        Student s = modelMapper.map(studentCreateDto, Student.class);
        return modelMapper.map(studentRepository.save(s), StudentDto.class);
    }

    public StudentDto update(StudentUpdateDto studentUpdateDto) {
        Student s = studentRepository.findById(studentUpdateDto.getId()).orElseThrow(
                () -> new StudentNotFoundException(studentUpdateDto.getId())
        );
        return modelMapper.map(studentRepository.save(s), StudentDto.class);
    }

    public List<StudentDto> list() {
        return studentRepository.findAll().stream().map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }

    public List<LessonDto> lessonList(StudentLessonListDto studentLessonListDto) {
        Student student = getStudent(studentLessonListDto.getStudentId());
        return student.getLessons().stream().map(lesson -> modelMapper.map(lesson, LessonDto.class))
                .collect(Collectors.toList());
    }

    public List<StudentDto> registerToLesson(StudentLessonDto studentLessonDto) {
        Student student = getStudent(studentLessonDto.getStudentId());
        List<Student> students = lessonService.registerStudent(studentLessonDto.getLessonId(), student);
        return students.stream().map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }

    public List<StudentDto> deleteToLesson(StudentLessonDto studentLessonDto) {
        Student student = getStudent(studentLessonDto.getStudentId());
        List<Student> students = lessonService.deleteStudent(studentLessonDto.getLessonId(), student);
        return students.stream().map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }

    private Student getStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(studentId)
        );

    }
}
