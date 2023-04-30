package ru.mpei.java_db.dao;

import org.springframework.stereotype.Repository;
import ru.mpei.java_db.domain.Student;

import java.util.List;

@Repository
public interface StudentDao {
    void insert(Student student);
    List<Student> getByGroup(String group);


}
