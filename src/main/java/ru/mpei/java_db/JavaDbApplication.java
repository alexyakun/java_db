package ru.mpei.java_db;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.java_db.Servise.AverageMark;
import ru.mpei.java_db.dao.MarksDao;
import ru.mpei.java_db.dao.StudentDao;
import ru.mpei.java_db.dao.StudentMarksDao;
import ru.mpei.java_db.domain.Mark;
import ru.mpei.java_db.domain.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaDbApplication {

	public static void main(String[] args) throws SQLException {

		ConfigurableApplicationContext context = SpringApplication.run(JavaDbApplication.class, args);
		StudentDao dao1 = context.getBean(StudentDao.class);
		MarksDao dao2 = context.getBean(MarksDao.class);
		StudentMarksDao dao3 = context.getBean(StudentMarksDao.class);

		dao1.insert(new Student(2,"alex","yakunin","vladimirovich", "8",2019 ));
		dao2.insert((new Mark(2,"python",5)));
		dao2.insert((new Mark(3,"java",5)));
		dao3.insert(2,2,2);
		dao3.insert(3,2,3);

		dao1.insert(new Student(3,"alex1","yakunin1","vladimirovich1", "9",2019 ));
		dao2.insert((new Mark(4,"python",3)));
		dao3.insert(4,3,4);

		dao1.insert(new Student(4,"vlad","vladov","michailovich", "9",2019 ));
		dao2.insert((new Mark(5,"java",3)));
		dao3.insert(5,4,5);

		dao1.insert(new Student(5,"mark","borisov","olegovich", "9",2019 ));
		dao2.insert((new Mark(6,"java",2)));
		dao3.insert(6,5,6);


		AverageMark averageMark = new AverageMark();
		averageMark.Calulate(dao1,dao3,dao2,"8","python");
		averageMark.Calulate(dao1,dao3,dao2,"8","java");
		averageMark.Calulate(dao1,dao3,dao2,"9","python");
		averageMark.Calulate(dao1,dao3,dao2,"9","java");

		Console.main(args);
	}

}
