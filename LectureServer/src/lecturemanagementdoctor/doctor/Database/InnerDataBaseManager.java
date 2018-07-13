package lecturemanagementdoctor.doctor.Database;

//import com.sun.org.glassfish.gmbal.Description;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.Lecturedb;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.LectureRecord;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.StudentRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.*;
import static lecturemanagementdoctor.doctor.ref.*;

/**
 *
 * @author Saad Alenany
 */
public class InnerDataBaseManager {

    public InnerDataBaseManager(String dbName) {
        try {
            Class.forName("org.h2.Driver");
            String DBURL = "jdbc:h2:databases/"+dbName;
            Connection connection = DriverManager.getConnection(DBURL);
            create_innerDB = DSL.using(connection, SQLDialect.H2);

            createTablesIfNotExist();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InnerDataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Description(" This will create_innerDB the Database schema & tables if they're not exist ")
    private void createTablesIfNotExist(){
        create_innerDB.createSchemaIfNotExists(Lecturedb.LECTUREDB)
                .execute();
        create_innerDB.createTableIfNotExists(Student.STUDENT)
                .column(Student.STUDENT.STUDENT_ID)
                .column(Student.STUDENT.STUDENT_NAME)
                .column(Student.STUDENT.STUDENT_EMAIL)
                .column(Student.STUDENT.STUDENT_PASSWORD)
                .column(Student.STUDENT.STUDENT_DEPARTMENT)
                .column(Student.STUDENT.STUDENT_PHONE)
                .column(Student.STUDENT.STUDENT_GENDER)
                .constraints(
                        DSL.constraint("PK_STUDENT_ID").primaryKey(Student.STUDENT.STUDENT_ID),
                        DSL.unique(Student.STUDENT.STUDENT_NAME),
                        DSL.unique(Student.STUDENT.STUDENT_EMAIL)
                        )
                .execute();
        create_innerDB.createTableIfNotExists(Lecture.LECTURE)
                .column(Lecture.LECTURE.LECTURE_ID)
                .column(Lecture.LECTURE.LECTURE_DATE)
                .constraints(
                        DSL.constraint("PK_LECTURE_ID").primaryKey(Lecture.LECTURE.LECTURE_ID),
                        DSL.unique(Lecture.LECTURE.LECTURE_DATE)
                )
                .execute();
        create_innerDB.createTableIfNotExists(Slide.SLIDE)
                .column(Slide.SLIDE.SLIDE_ID)
                .column(Slide.SLIDE.SLIDE_PATH)
                .column(Slide.SLIDE.LECTURE_ID)
                .constraints(
                        DSL.constraint("PK_SLIDE_ID").primaryKey(Slide.SLIDE.SLIDE_ID),
                        DSL.constraint("FK_SLIDE_LECTURE_ID")
                                .foreignKey(Slide.SLIDE.LECTURE_ID)
                                .references(Lecture.LECTURE,Lecture.LECTURE.LECTURE_ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
        create_innerDB.createTableIfNotExists(Quiz.QUIZ)
                .column(Quiz.QUIZ.QUIZ_ID)
                .column(Quiz.QUIZ.QUIZ_NAME)
                .constraints(
                        DSL.constraint("PK_QUIZ_ID").primaryKey(Quiz.QUIZ.QUIZ_ID)
                )
                .execute();
        create_innerDB.createTableIfNotExists(Quizquestion.QUIZQUESTION)
                .column(Quizquestion.QUIZQUESTION.QUESTION_ID)
                .column(Quizquestion.QUIZQUESTION.NUMBEROFCHOICES)
                .column(Quizquestion.QUIZQUESTION.QUESTION_DATA)
                .column(Quizquestion.QUIZQUESTION.RIGHT_ANSWER)
                .column(Quizquestion.QUIZQUESTION.QUIZ_ID)
                .constraints(
                        DSL.constraint("PK_QUIZ_QUESTION_ID").primaryKey(Quizquestion.QUIZQUESTION.QUESTION_ID),
                        DSL.constraint("FK_QUIZ_QUESTION_QUIZ_ID")
                                .foreignKey(Quizquestion.QUIZQUESTION.QUIZ_ID)
                                .references(Quiz.QUIZ, Quiz.QUIZ.QUIZ_ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
        create_innerDB.createTableIfNotExists(Choicedata.CHOICEDATA)
                .column(Choicedata.CHOICEDATA.CHOICE_ID)
                .column(Choicedata.CHOICEDATA.CHOICE_CONTENT)
                .column(Choicedata.CHOICEDATA.QUESTION_ID)
                .constraints(
                        DSL.constraint("PK_CHOICE_DATA_ID").primaryKey(Choicedata.CHOICEDATA.CHOICE_ID),
                        DSL.constraint("FK_CHOICE_DATA_QUIZ_QUESTION_ID")
                                .foreignKey(Choicedata.CHOICEDATA.QUESTION_ID)
                                .references(Quizquestion.QUIZQUESTION, Quizquestion.QUIZQUESTION.QUESTION_ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
        create_innerDB.createTableIfNotExists(Studentdegrees.STUDENTDEGREES)
                .column(Studentdegrees.STUDENTDEGREES.STUDENT_ID)
                .column(Studentdegrees.STUDENTDEGREES.QUIZ_ID)
                .column(Studentdegrees.STUDENTDEGREES.DEGREE)
                .column(Studentdegrees.STUDENTDEGREES.TOTALDEGREE)
                .constraints(
                        DSL.constraint("FK_STUDENT_DEGREE_STUDENT_ID")
                                .foreignKey(Studentdegrees.STUDENTDEGREES.STUDENT_ID)
                                .references(Student.STUDENT, Student.STUDENT.STUDENT_ID)
                                .onDeleteCascade()
                                .onUpdateCascade(),
                        DSL.constraint("FK_STUDENT_DEGREE_QUIZ_ID")
                                .foreignKey(Studentdegrees.STUDENTDEGREES.QUIZ_ID)
                                .references(Quiz.QUIZ, Quiz.QUIZ.QUIZ_ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                ).execute();
        create_innerDB.createTableIfNotExists(Absence.ABSENCE)
                .column(Absence.ABSENCE.STUDENT_ID)
                .column(Absence.ABSENCE.LECTURE_ID)
                .constraints(
                        DSL.constraint("FK_ABSENCE_STUDENT_ID")
                                .foreignKey(Absence.ABSENCE.STUDENT_ID)
                                .references(Student.STUDENT, Student.STUDENT.STUDENT_ID)
                                .onDeleteCascade()
                                .onUpdateCascade(),
                        DSL.constraint("FK_ABCENCE_LECTURE_ID")
                                .foreignKey(Absence.ABSENCE.LECTURE_ID)
                                .references(Lecture.LECTURE, Lecture.LECTURE.LECTURE_ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                        ).execute();
    }

    public void truncateTables(){
        create_innerDB.truncate(Absence.ABSENCE);
        create_innerDB.truncate(Studentdegrees.STUDENTDEGREES);
        create_innerDB.truncate(Choicedata.CHOICEDATA);
        create_innerDB.truncate(Quizquestion.QUIZQUESTION);
        create_innerDB.truncate(Quiz.QUIZ);
        create_innerDB.truncate(Slide.SLIDE);
        create_innerDB.truncate(Lecture.LECTURE);
        create_innerDB.truncate(Student.STUDENT);
    }

    public void close(){
        create_innerDB.close();
    }

//    @Description(" This will insert a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be inserted before the sub")
    public Record insert(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Lecturedb.LECTUREDB+"."+DSL.table(DSL.name(o.getClass().getSimpleName()));
        String SQL = "INSERT INTO " + tableName + " ( ";
        for (int i = 0; i < fields.length; i++) {
            if (!o.getClass().getSimpleName().equals("StudentDegrees")
                    && !o.getClass().getSimpleName().equals("Absence")){
                if (i == 0){
                    continue;
                }
            }
            SQL += "\""+DSL.field(fields[i].getName())+"\"";
            if (i != fields.length-1){
                SQL += " , ";
            }
        }
        SQL += " ) VALUES ( ";
        for (int i = 0; i < fields.length; i++) {
            if (!o.getClass().getSimpleName().equals("StudentDegrees")
                    && !o.getClass().getSimpleName().equals("Absence")){
                if (i == 0){
                    continue;
                }
            }
            try {
                if (fields[i].getType() == Integer.class || fields[i].getType() == int.class || fields[i].getType() == boolean.class){
                    SQL += fields[i].get(o);
                }else{
                    SQL += "'"+fields[i].get(o)+"'";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i != fields.length-1){
                SQL += " , ";
            }
        }
        SQL += " );";
        System.out.println(SQL);
        if(create_innerDB.query(SQL).execute() == 1){
            Result<Record> r = create_innerDB.selectFrom(tableName).fetch();
            return r.get(r.size()-1);
        }
        return null;
    }

//    @Description(" This will update a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be updated as well")
    public Record update(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Lecturedb.LECTUREDB+".\""+DSL.table(o.getClass().getSimpleName())+"\"";
        String SQL = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < fields.length; i++) {
            SQL += DSL.field(tableName+"."+DSL.name(fields[i].getName()))+" = ";

            try {
                if (fields[i].getType() == Integer.class || fields[i].getType() == int.class || fields[i].getType() == boolean.class){
                    SQL += fields[i].get(o);
                }else{
                    SQL += "'"+fields[i].get(o)+"'";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (i != fields.length-1){
                SQL += " , ";
            }
        }
        try {
            SQL += " WHERE "+DSL.field(tableName+"."+DSL.name(fields[0].getName().toLowerCase()))+" = "+fields[0].get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        SQL += " ;";
        System.out.println(SQL);
        if(create_innerDB.query(SQL).execute() == 1){
            Result<Record> r = create_innerDB.selectFrom(tableName).fetch();
            return r.get(r.size()-1);
        }
        return null;
    }

//    @Description(" This will delete a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be deleted after the sub")
    public boolean delete(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Lecturedb.LECTUREDB+"."+DSL.table(DSL.name(o.getClass().getSimpleName()));
        String SQL = null;
        try {
            SQL = "DELETE FROM "+tableName+" WHERE "+DSL.field(tableName+"."+DSL.name(fields[0].getName().toLowerCase()))+" = "+fields[0].get(o)+" ;";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(SQL);
        return create_innerDB.query(SQL).execute() == 1;
    }

//    @Description(" This will return a List of Record objects of a database table ")
    public Result<Record> getAll(String tableName){
        return create_innerDB.selectFrom(Lecturedb.LECTUREDB+"."+DSL.field(DSL.name(tableName))).fetch();
    }

    public int checkStudentUsernameAndPassword(String username, String password){
        return create_innerDB.selectFrom(Student.STUDENT).where(Student.STUDENT.STUDENT_NAME.eq(username)).and(Student.STUDENT.STUDENT_PASSWORD.eq(password)).fetch().size();
    }

    public int getStudentIDByName(String username){
        return create_innerDB.selectFrom(Student.STUDENT).where(Student.STUDENT.STUDENT_NAME.eq(username)).fetchAny().getStudentId();
    }

    public LectureRecord getLectureByDate(String date){
        return create_innerDB.selectFrom(Lecture.LECTURE).where(Lecture.LECTURE.LECTURE_DATE.eq(date)).fetchAny();
    }
}
