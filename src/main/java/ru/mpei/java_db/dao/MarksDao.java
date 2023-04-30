package ru.mpei.java_db.dao;

import ru.mpei.java_db.domain.Mark;
import ru.mpei.java_db.domain.Student;

import java.util.OptionalInt;
import java.util.OptionalLong;

public interface MarksDao {
    void insert(Mark mark);
    OptionalInt getById(long id, String course_name);
}
