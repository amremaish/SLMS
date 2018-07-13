package lecturemanagementdoctor.doctor.Database.pojos;

/**
 *
 * @author Saad Alenany
 */
public class Quiz {

    public int quiz_id;
    public String quiz_name;

    public Quiz(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public Quiz(int quiz_id, String quiz_name) {
        this.quiz_id = quiz_id;
        this.quiz_name = quiz_name;
    }

    public Quiz(String quiz_name, int course_id) {
        this.quiz_name = quiz_name;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int Quiz_id) {
        this.quiz_id = Quiz_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quiz_id=" + quiz_id + ", quiz_name=" + quiz_name + '}';
    }

}
