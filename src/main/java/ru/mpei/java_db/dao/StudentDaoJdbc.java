package ru.mpei.java_db.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.java_db.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@Repository
public class StudentDaoJdbc implements StudentDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudentDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {

        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }
    @Override
    public void insert(Student student){
        namedParameterJdbcOperations.update("insert into students (id, surname, name, otchestvo, group_name, year_in) values (:id, :surname,:name,:otchestvo,:group_name,:year)",
                Map.of("id", student.getId(), "surname", student.getSurname(), "name",student.getName(), "otchestvo",student.getOtchestvo(),"group_name",student.getGroupName(),"year",student.getYear()));

    }

    @Override
    public List<Student> getByGroup(String group) {
        Map<String, Object> params = Collections.singletonMap("group_name", group);
        return namedParameterJdbcOperations.query("select * from students where group_name=:group_name",params,new StudentMapper());
    }


    private static class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String surname = resultSet.getString("surname");
            String name = resultSet.getString("name");
            String otchestvo = resultSet.getString("otchestvo");
            String group_name = resultSet.getString("group_name");
            int year = resultSet.getInt("year_in");

            return new Student(id, name, surname,otchestvo,group_name,year);
        }
    }
}
