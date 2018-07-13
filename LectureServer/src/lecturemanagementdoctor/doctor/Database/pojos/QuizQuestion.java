package lecturemanagementdoctor.doctor.Database.pojos;

/**
 *
 * @author Saad Alenany
 */
public class QuizQuestion {

    public int question_id;
    public int numberofchoices;
    public String question_data;
    public int right_answer;
    public int quiz_id;

    public QuizQuestion(int question_id, int numberofchoices, String question_data, int right_answer, int quiz_id) {
        this.question_id = question_id;
        this.numberofchoices = numberofchoices;
        this.question_data = question_data;
        this.right_answer = right_answer;
        this.quiz_id = quiz_id;
    }

    public QuizQuestion(int numberofchoices, String question_data, int right_answer, int quiz_id) {
        this.numberofchoices = numberofchoices;
        this.question_data = question_data;
        this.right_answer = right_answer;
        this.quiz_id = quiz_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int QuizQuestion_id) {
        this.question_id = QuizQuestion_id;
    }

    public int getNumberofchoices() {
        return numberofchoices;
    }

    public void setNumberofchoices(int numberofchoices) {
        this.numberofchoices = numberofchoices;
    }

    public String getQuestion_data() {
        return question_data;
    }

    public void setQuestion_data(String question_data) {
        this.question_data = question_data;
    }

    public int getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(int right_answer) {
        this.right_answer = right_answer;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" + "question_id=" + question_id + ", numberofchoices=" + numberofchoices + ", question_data=" + question_data + ", right_answer=" + right_answer + ", quiz_id=" + quiz_id + '}';
    }

}
