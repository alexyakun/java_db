package ru.mpei.java_db.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.java_db.domain.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.OptionalInt;
import java.util.OptionalLong;

@Repository
public class MarksDaoJdbc implements MarksDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public MarksDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {

        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Mark mark) {
        namedParameterJdbcOperations.update("insert into marks (id, course_name, mark) values (:id, :course_name,:mark)",
                Map.of("id", mark.getId(), "course_name", mark.getCourse_name(), "mark",mark.getValue()));
    }
    @Override
    public OptionalInt getById(long id, String course_name) {

        Map<String, Object> params = Map.of("id", id,"course_name",course_name);
        try{
            return namedParameterJdbcOperations.queryForObject(
                    "select mark from marks where id = :id and course_name = :course_name", params, new MarksDaoJdbc.IdMapper()
            );} catch (EmptyResultDataAccessException e){
            return OptionalInt.empty();
        }
    }
    private static class IdMapper implements RowMapper<OptionalInt> {

        @Override
        public OptionalInt mapRow(ResultSet resultSet, int i) throws SQLException {

//            long id = resultSet.getLong("id");
//            long student_id = resultSet.getLong("student_id");
            int marks_id = resultSet.getInt("mark");


            return OptionalInt.of(marks_id);
        }
    }
}
