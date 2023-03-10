package com.talhacgdem.lessonmanagement.repository;

import com.talhacgdem.lessonmanagement.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
