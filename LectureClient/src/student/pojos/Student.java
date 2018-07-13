package student.pojos;

/**
 *
 * @author Saad Alenany
 */
public class Student{

    public int student_id;
    public String student_name;
    public String student_email;
    public String student_password;
    public String student_department;
    public String student_phone;
    public String student_gender;

    public Student() {
    }

    public Student(String student_name, String student_email, String student_password, String student_department, String student_phone, String student_gender) {
        this.student_name = student_name;
        this.student_email = student_email;
        this.student_password = student_password;
        this.student_department = student_department;
        this.student_phone = student_phone;
        this.student_gender = student_gender;
    }

    public Student(int student_id, String student_name, String student_email, String student_password, String student_department, String student_phone, String student_gender) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_email = student_email;
        this.student_password = student_password;
        this.student_department = student_department;
        this.student_phone = student_phone;
        this.student_gender = student_gender;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    public String getStudent_department() {
        return student_department;
    }

    public void setStudent_department(String student_department) {
        this.student_department = student_department;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", student_email='" + student_email + '\'' +
                ", student_password='" + student_password + '\'' +
                ", student_department='" + student_department + '\'' +
                ", student_phone='" + student_phone + '\'' +
                ", student_gender='" + student_gender + '\'' +
                '}';
    }
}
