package com.talhacgdem.lessonmanagement.service;

import com.talhacgdem.lessonmanagement.dto.request.StudentCreateDto;
import com.talhacgdem.lessonmanagement.dto.response.StudentDto;
import com.talhacgdem.lessonmanagement.entity.Student;
import com.talhacgdem.lessonmanagement.exception.StudentNotFoundException;
import com.talhacgdem.lessonmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentServiceTest {

    private StudentService service;
    private LessonService lessonService;
    private ModelMapper modelMapper;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        lessonService = Mockito.mock(LessonService.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        studentRepository = Mockito.mock(StudentRepository.class);
        service = new StudentService(studentRepository, modelMapper, lessonService);
    }

    @Test
    public void testAddCalledWithValidRequest_itShouldReturnValidLessonDto() {
        StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                .name("Talha")
                .surname("Çiğdem")
                .build();

        Student student = Student.builder()
                .id(1L)
                .name("Talha")
                .surname("Çiğdem")
                .build();

        StudentDto studentDto = StudentDto.builder()
                .name("Talha")
                .surname("Çiğdem")
                .build();

        Mockito.when(modelMapper.map(studentCreateDto, Student.class)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(modelMapper.map(student, StudentDto.class)).thenReturn(studentDto);

        StudentDto result = service.add(studentCreateDto);

        assertEquals(result, studentDto);
        Mockito.verify(modelMapper).map(studentCreateDto, Student.class);
        Mockito.verify(studentRepository).save(student);
        Mockito.verify(modelMapper).map(student, StudentDto.class);
    }

    @Test
    public void testGetStudent_whenStudentIsExist_shouldReturnStudent() {
        Student student = Student.builder()
                .id(1L)
                .name("Talha")
                .surname("Çiğdem")
                .build();
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = service.getStudent(1L);
        assertEquals(result, student);
    }

    @Test
    public void testGetStudent_whenStudentDoesNotExist_shouldThrowStudentNotFoundException() {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> service.getStudent(1L));
    }


}