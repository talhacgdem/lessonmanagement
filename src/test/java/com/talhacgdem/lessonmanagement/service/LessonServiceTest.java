package com.talhacgdem.lessonmanagement.service;

import com.talhacgdem.lessonmanagement.dto.request.LessonCreateDto;
import com.talhacgdem.lessonmanagement.dto.response.LessonDto;
import com.talhacgdem.lessonmanagement.entity.Lesson;
import com.talhacgdem.lessonmanagement.entity.Student;
import com.talhacgdem.lessonmanagement.exception.LessonNotFoundException;
import com.talhacgdem.lessonmanagement.exception.LessonOutOfQuotaException;
import com.talhacgdem.lessonmanagement.exception.StudentAlreadyRegisteredLessonException;
import com.talhacgdem.lessonmanagement.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class LessonServiceTest {

    private LessonService service;
    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        lessonRepository = Mockito.mock(LessonRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        service = new LessonService(lessonRepository, modelMapper);
    }

    @Test
    public void testAddCalledWithValidRequest_itShouldReturnValidLessonDto() {
        LessonCreateDto lessonCreateDto = LessonCreateDto.builder()
                .name("Matematik")
                .quota(2)
                .build();

        Lesson lesson = Lesson.builder()
                .id(1L)
                .name("Matematik")
                .quota(2)
                .build();

        LessonDto lessonDto = LessonDto.builder()
                .name("Matematik")
                .quota(2)
                .build();

        Mockito.when(modelMapper.map(lessonCreateDto, Lesson.class)).thenReturn(lesson);
        Mockito.when(lessonRepository.save(lesson)).thenReturn(lesson);
        Mockito.when(modelMapper.map(lesson, LessonDto.class)).thenReturn(lessonDto);

        LessonDto result = service.add(lessonCreateDto);

        assertEquals(result, lessonDto);
        Mockito.verify(modelMapper).map(lessonCreateDto, Lesson.class);
        Mockito.verify(lessonRepository).save(lesson);
        Mockito.verify(modelMapper).map(lesson, LessonDto.class);
    }

    @Test
    public void testGetLesson_whenLessonIsExist_shouldReturnLesson() {
        Lesson lesson = Lesson.builder()
                .id(1L)
                .name("Matematik")
                .quota(2)
                .build();

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));

        Lesson result = service.getLesson(1L);

        assertEquals(result, lesson);
    }

    @Test
    public void testGetLesson_whenLessonDoesNotExist_shouldThrowLessonNotFoundException() {
        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(LessonNotFoundException.class, () -> service.getLesson(1L));
    }

    @Test
    public void testRegisterStudent_whenStudentIsNotTakingTheLessonAndLessonHaveEnoughQuota_shouldReturnListOfStudent() {
        Student student = Student.builder()
                .id(2L)
                .name("Muhammed Talha")
                .surname("Çiğdem")
                .build();
        long id = 1L;
        Lesson lesson = Lesson.builder()
                .id(id)
                .name("Matematik")
                .quota(2)
                .students(new ArrayList<>())
                .build();
        List<Student> students = new ArrayList<>();
        students.add(student);
        Lesson ls = Lesson.builder()
                .id(id)
                .name("Matematik")
                .quota(2)
                .students(students)
                        .build();
        Mockito.when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));
        Mockito.when(lessonRepository.save(lesson)).thenReturn(ls);
        List<Student> result = service.registerStudent(id, student);
        assertEquals(result, students);
    }

    @Test
    public void testRegisterStudent_whenStudentIsTakingTheLesson_shouldThrowStudentAlreadyRegisteredLessonException() {
        Student student = Student.builder()
                .id(1L)
                .name("Muhammed Talha")
                .surname("Çiğdem")
                .lessons(List.of())
                .build();

        Lesson lesson = Lesson.builder()
                .id(1L)
                .name("Matematik")
                .quota(2)
                .students(List.of(student))
                .build();

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        assertThrows(StudentAlreadyRegisteredLessonException.class, () -> service.registerStudent(lesson.getId(), student));
    }

    @Test
    public void testRegisterStudent_whenStudentIsNotTakingTheLessonAndLessonHaveNotEnoughQuota_shouldThrowLessonOutOfQuotaException() {
        Student student = Student.builder()
                .id(2L)
                .name("Muhammed Talha")
                .surname("Çiğdem")
                .build();
        long id = 1L;
        Lesson ls = Lesson.builder()
                .id(id)
                .name("Matematik")
                .quota(0)
                .students(new ArrayList<>())
                .build();
        Mockito.when(lessonRepository.findById(id)).thenReturn(Optional.of(ls));
        assertThrows(LessonOutOfQuotaException.class, () -> service.registerStudent(id, student));
    }

//    @Test
//    public void testUpdate_whenLessonIsExist_shouldReturnLessonDto(){
//        LessonUpdateDto lessonUpdateDto = LessonUpdateDto.builder()
//                .name("Fizik")
//                .quota(2)
//                .build();
//
//        Lesson lesson = Lesson.builder()
//                .id(1L)
//                .name("Matematik")
//                .quota(2)
//                .build();
//
//
//    }

}