package lecturemanagementdoctor.doctor.Database.pojos;

/**
 *
 * @author Saad Alenany
 */
public class Doctor{

    public int doctor_id;
    public String doctor_name;
    public String doctor_password;
    public String doctor_email;
    public String doctor_gender;
    public String doctor_course;
    public String doctor_phone;
    public String doctor_department;
    public String doctor_database;

    public Doctor() {
    }

    public Doctor(String doctor_name, String doctor_password, String doctor_email, String doctor_gender, String doctor_course, String doctor_phone, String doctor_department, String doctor_database) {
        this.doctor_name = doctor_name;
        this.doctor_password = doctor_password;
        this.doctor_email = doctor_email;
        this.doctor_gender = doctor_gender;
        this.doctor_course = doctor_course;
        this.doctor_phone = doctor_phone;
        this.doctor_department = doctor_department;
        this.doctor_database = doctor_database;
    }

    public Doctor(int doctor_id, String doctor_name, String doctor_password, String doctor_email, String doctor_gender, String doctor_course, String doctor_phone, String doctor_department, String doctor_database) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_password = doctor_password;
        this.doctor_email = doctor_email;
        this.doctor_gender = doctor_gender;
        this.doctor_course = doctor_course;
        this.doctor_phone = doctor_phone;
        this.doctor_department = doctor_department;
        this.doctor_database = doctor_database;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_gender() {
        return doctor_gender;
    }

    public void setDoctor_gender(String doctor_gender) {
        this.doctor_gender = doctor_gender;
    }

    public String getDoctor_course() {
        return doctor_course;
    }

    public void setDoctor_course(String doctor_course) {
        this.doctor_course = doctor_course;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }

    public String getDoctor_department() {
        return doctor_department;
    }

    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }

    public String getDoctor_database() {
        return doctor_database;
    }

    public void setDoctor_database(String doctor_database) {
        this.doctor_database = doctor_database;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", doctor_password='" + doctor_password + '\'' +
                ", doctor_email='" + doctor_email + '\'' +
                ", doctor_gender='" + doctor_gender + '\'' +
                ", doctor_course='" + doctor_course + '\'' +
                ", doctor_phone='" + doctor_phone + '\'' +
                ", doctor_department='" + doctor_department + '\'' +
                ", doctor_database='" + doctor_database + '\'' +
                '}';
    }
}
