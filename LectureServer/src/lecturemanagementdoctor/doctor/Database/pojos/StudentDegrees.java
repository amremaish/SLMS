package lecturemanagementdoctor.doctor.Database.pojos;

public class StudentDegrees {

    public Integer student_id;
    public Integer quiz_id;
    public Integer degree;
    public Integer total_degree;

    public StudentDegrees(Integer student_id, Integer quiz_id, Integer degree, Integer total_degree) {
        this.student_id = student_id;
        this.quiz_id = quiz_id;
        this.degree = degree;
        this.total_degree = total_degree;
    }

    public StudentDegrees() {

    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getTotal_degree() {
        return total_degree;
    }

    public void setTotal_degree(Integer total_degree) {
        this.total_degree = total_degree;
    }

    @Override
    public String toString() {
        return "StudentDegrees{" +
                "student_id=" + student_id +
                ", quiz_id=" + quiz_id +
                ", degree=" + degree +
                ", total_degree=" + total_degree +
                '}';
    }
}
