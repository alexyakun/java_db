package ru.mpei.java_db.dao;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface StudentMarksDao {
    void insert(long id, long student_id, long mark_id);
    List<OptionalLong> getById(long id);
}
