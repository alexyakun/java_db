package ru.mpei.java_db.Servise;

import ru.mpei.java_db.dao.MarksDao;
import ru.mpei.java_db.dao.StudentDao;
import ru.mpei.java_db.dao.StudentMarksDao;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class AverageMark {
    public void Calulate(StudentDao sDao, StudentMarksDao sMDao, MarksDao mDao,String groupName, String courseName ){
        List<Long> stId = sDao.getByGroup(groupName).stream().map(s->s.getId()).collect(Collectors.toList());

        double summa = 0;
        int num = 0;
        for(long id : stId){
            for(OptionalLong mark_id:sMDao.getById(id)) {
                if (mark_id.isPresent()) {

                    if (mDao.getById(mark_id.getAsLong(), courseName).isPresent()) {
                        int mark = mDao.getById(mark_id.getAsLong(), courseName).getAsInt();
                        summa += mark;
                        num += 1;
                    }
                }
            }
        }
        if(num > 0 ){

            System.out.println("Средняя оценка для группы "+groupName+" по курсу "+courseName+" = " + summa/num);
        }
        else {
            System.out.println("Нет оценок для группы "+ groupName+ " по курсу "+courseName);
        }
    }
}
