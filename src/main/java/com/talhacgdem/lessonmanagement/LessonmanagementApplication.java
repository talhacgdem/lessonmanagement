package com.talhacgdem.lessonmanagement;

import com.talhacgdem.lessonmanagement.entity.Lesson;
import com.talhacgdem.lessonmanagement.entity.Student;
import com.talhacgdem.lessonmanagement.repository.LessonRepository;
import com.talhacgdem.lessonmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class LessonmanagementApplication implements CommandLineRunner {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(LessonmanagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Lesson l;
        l = Lesson.builder().name("Matematik").quota(2).build();
        lessonRepository.save(l);
        l = Lesson.builder().name("Fizik").quota(2).build();
        lessonRepository.save(l);
        l = Lesson.builder().name("Türkçe").quota(2).build();
        lessonRepository.save(l);

        Student s;
        s = Student.builder().name("Talha").surname("Çiğdem").build();
        studentRepository.save(s);
        s = Student.builder().name("Emine").surname("Özbek").build();
        studentRepository.save(s);
        s = Student.builder().name("Emrah").surname("Yılmaz").build();
        studentRepository.save(s);
    }
}
