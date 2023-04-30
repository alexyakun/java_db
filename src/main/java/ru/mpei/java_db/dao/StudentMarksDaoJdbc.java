package ru.mpei.java_db.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.java_db.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class StudentMarksDaoJdbc implements StudentMarksDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudentMarksDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {

        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(long id, long student_id, long mark_id) {
        namedParameterJdbcOperations.update("insert into students_marks (id, student_id, marks_id) values (:id, :student_id,:marks_id)",
                Map.of("id", id, "student_id", student_id, "marks_id",mark_id));
    }
    @Override
    public List<OptionalLong> getById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
//        try{
        return namedParameterJdbcOperations.query(
                "select marks_id from students_marks where student_id = :id", params, new IdMapper()
        );
//        }
//        catch (EmptyResultDataAccessException e){
//            return Optional.empty();
//        }
    }
    private static class IdMapper implements RowMapper<OptionalLong> {

        @Override
        public OptionalLong mapRow(ResultSet resultSet, int i) throws SQLException {

//            long id = resultSet.getLong("id");
//            long student_id = resultSet.getLong("student_id");
            long marks_id = resultSet.getLong("marks_id");


            return OptionalLong.of(marks_id);
        }
    }
}
