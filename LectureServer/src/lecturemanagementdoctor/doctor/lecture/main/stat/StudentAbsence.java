package lecturemanagementdoctor.doctor.lecture.main.stat;

public class StudentAbsence {
    String student_name;
    String student_department;
    String lecture_date;

    public StudentAbsence(String student_name, String student_department, String lecture_date) {
        this.student_name = student_name;
        this.student_department = student_department;
        this.lecture_date = lecture_date;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_department() {
        return student_department;
    }

    public void setStudent_department(String student_department) {
        this.student_department = student_department;
    }

    public String getLecture_date() {
        return lecture_date;
    }

    public void setLecture_date(String lecture_date) {
        this.lecture_date = lecture_date;
    }

    @Override
    public String toString() {
        return "StudentAbsence{" +
                "student_name='" + student_name + '\'' +
                ", student_department='" + student_department + '\'' +
                ", lecture_date='" + lecture_date + '\'' +
                '}';
    }
}
