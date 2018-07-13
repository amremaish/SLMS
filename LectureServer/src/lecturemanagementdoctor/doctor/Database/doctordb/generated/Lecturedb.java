/*
 * This file is generated by jOOQ.
 */
package lecturemanagementdoctor.doctor.Database.doctordb.generated;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Choicedata;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Lecture;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quiz;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quizquestion;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Slide;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Student;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Studentdegrees;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Lecturedb extends SchemaImpl {

    private static final long serialVersionUID = 646199307;

    /**
     * The reference instance of <code>lecturedb</code>
     */
    public static final Lecturedb LECTUREDB = new Lecturedb();

    /**
     * The table <code>lecturedb.Absence</code>.
     */
    public final Absence ABSENCE = Absence.ABSENCE;

    /**
     * The table <code>lecturedb.ChoiceData</code>.
     */
    public final Choicedata CHOICEDATA = Choicedata.CHOICEDATA;

    /**
     * The table <code>lecturedb.Lecture</code>.
     */
    public final Lecture LECTURE = Lecture.LECTURE;

    /**
     * The table <code>lecturedb.Quiz</code>.
     */
    public final Quiz QUIZ = Quiz.QUIZ;

    /**
     * The table <code>lecturedb.QuizQuestion</code>.
     */
    public final Quizquestion QUIZQUESTION = Quizquestion.QUIZQUESTION;

    /**
     * The table <code>lecturedb.Slide</code>.
     */
    public final Slide SLIDE = Slide.SLIDE;

    /**
     * The table <code>lecturedb.Student</code>.
     */
    public final Student STUDENT = Student.STUDENT;

    /**
     * The table <code>lecturedb.StudentDegrees</code>.
     */
    public final Studentdegrees STUDENTDEGREES = Studentdegrees.STUDENTDEGREES;

    /**
     * No further instances allowed
     */
    private Lecturedb() {
        super("lecturedb", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Absence.ABSENCE,
            Choicedata.CHOICEDATA,
            Lecture.LECTURE,
            Quiz.QUIZ,
            Quizquestion.QUIZQUESTION,
            Slide.SLIDE,
            Student.STUDENT,
            Studentdegrees.STUDENTDEGREES);
    }
}
