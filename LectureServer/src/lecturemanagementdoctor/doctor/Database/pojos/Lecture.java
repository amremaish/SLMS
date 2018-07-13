package lecturemanagementdoctor.doctor.Database.pojos;

/**
 *
 * @author Saad Alenany
 */
public class Lecture {

    public int lecture_id;
    public String lecture_date;

    public Lecture() {
    }

    public Lecture(String lecture_date) {
        this.lecture_date = lecture_date;
    }

    public Lecture(int lecture_id, String lecture_date) {
        this.lecture_id = lecture_id;
        this.lecture_date = lecture_date;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getLecture_date() {
        return lecture_date;
    }

    public void setLecture_date(String lecture_date) {
        this.lecture_date = lecture_date;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lecture_id=" + lecture_id +
                ", lecture_date='" + lecture_date + '\'' +
                '}';
    }
}
