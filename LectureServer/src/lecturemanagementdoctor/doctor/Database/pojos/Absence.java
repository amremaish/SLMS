package lecturemanagementdoctor.doctor.Database.pojos;

public class Absence {

    public Integer student_id;
    public Integer lecture_id;

    public Absence(Integer student_id, Integer lecture_id) {
        this.student_id = student_id;
        this.lecture_id = lecture_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(Integer lecture_id) {
        this.lecture_id = lecture_id;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "student_id=" + student_id +
                ", lecture_id=" + lecture_id +
                '}';
    }
}
