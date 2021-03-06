/*
 * This file is generated by jOOQ.
 */
package lecturemanagementdoctor.doctor.Database.doctordb.generated;


import javax.annotation.Generated;

import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Choicedata;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Lecture;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quiz;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quizquestion;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Slide;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Student;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Studentdegrees;


/**
 * Convenience access to all tables in lecturedb
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>lecturedb.Absence</code>.
     */
    public static final Absence ABSENCE = Absence.ABSENCE;

    /**
     * The table <code>lecturedb.ChoiceData</code>.
     */
    public static final Choicedata CHOICEDATA = Choicedata.CHOICEDATA;

    /**
     * The table <code>lecturedb.Lecture</code>.
     */
    public static final Lecture LECTURE = Lecture.LECTURE;

    /**
     * The table <code>lecturedb.Quiz</code>.
     */
    public static final Quiz QUIZ = Quiz.QUIZ;

    /**
     * The table <code>lecturedb.QuizQuestion</code>.
     */
    public static final Quizquestion QUIZQUESTION = Quizquestion.QUIZQUESTION;

    /**
     * The table <code>lecturedb.Slide</code>.
     */
    public static final Slide SLIDE = Slide.SLIDE;

    /**
     * The table <code>lecturedb.Student</code>.
     */
    public static final Student STUDENT = Student.STUDENT;

    /**
     * The table <code>lecturedb.StudentDegrees</code>.
     */
    public static final Studentdegrees STUDENTDEGREES = Studentdegrees.STUDENTDEGREES;
}
