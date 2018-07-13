package lecturemanagementdoctor.doctor.lecture.main.profile;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.Doctor;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Notify;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.openning.OpenningController;
import lecturemanagementdoctor.doctor.ref;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFXMLController implements Initializable {

    @FXML
    public Text username;
    @FXML
    public Text email;
    @FXML
    public Text course;
    @FXML
    public Text gender;
    @FXML
    public JFXTextField phone;
    @FXML
    public JFXTextField department;
    @FXML
    public JFXPasswordField oldpassword;
    @FXML
    public JFXPasswordField newpassword;
    @FXML
    public JFXPasswordField confirmpassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(ref.LOGGED_DOCTOR.getDoctor_name());
        email.setText(ref.LOGGED_DOCTOR.getDoctor_email());
        course.setText(ref.LOGGED_DOCTOR.getDoctor_course());
        gender.setText(ref.LOGGED_DOCTOR.getDoctor_gender());

        phone.setText(ref.LOGGED_DOCTOR.getDoctor_phone());
        department.setText(ref.LOGGED_DOCTOR.getDoctor_department());
    }

    public void onSaveChanges(ActionEvent actionEvent) {
        if (!checkFields()){
            return;
        }

        if (!saveChanges()){
            return;
        }
        Notify.ConfirmationMessage("Changes saved :)");
    }

    private boolean saveChanges() {
        ref.LOGGED_DOCTOR.setDoctor_phone(phone.getText());
        ref.LOGGED_DOCTOR.setDoctor_department(department.getText());
        ref.LOGGED_DOCTOR.setDoctor_password(newpassword.getText());

        return ref.databaseManagerOuter.update(ref.LOGGED_DOCTOR) != null;
    }

    private boolean checkFields() {

        if (phone.getText().isEmpty()){
            phone.setUnFocusColor(Color.RED);
            projectileError(phone,"Please Type your phone!");
            return false;
        }

        if (department.getText().isEmpty()){
            department.setUnFocusColor(Color.RED);
            projectileError(department,"Please Type your department!");
            return false;
        }

        if (oldpassword.getText().isEmpty()){
            oldpassword.setUnFocusColor(Color.RED);
            projectileError(oldpassword,"Please Type your password!");
            return false;
        }

        if (!ref.LOGGED_DOCTOR.getDoctor_password().equals(oldpassword.getText())){
            oldpassword.setUnFocusColor(Color.RED);
            projectileError(oldpassword,"Incorrect password!");
            return false;
        }

        if (newpassword.getText().isEmpty()){
            newpassword.setUnFocusColor(Color.RED);
            projectileError(newpassword,"Please Type your new password!");
            return false;
        }

        if (confirmpassword.getText().isEmpty()){
            confirmpassword.setUnFocusColor(Color.RED);
            projectileError(confirmpassword,"Please Type confirm your password!");
            return false;
        }

        if (!newpassword.getText().equals(confirmpassword.getText())){
            oldpassword.setUnFocusColor(Color.RED);
            projectileError(oldpassword,"passwords are mismatched!");
            return false;
        }

        return true;
    }

    public void onDeleteAccount(ActionEvent actionEvent) {
        File file = new File("databases/"+ref.LOGGED_DOCTOR.getDoctor_database()+".h2.db");
        try {
            ref.databaseManagerInner.close();
            String str = file.getCanonicalPath();
            System.out.println(str);
            if(file.delete()) {
                System.out.println("File deleted successfully");
                ref.create_outerDB.deleteFrom(Doctor.DOCTOR)
                        .where(Doctor.DOCTOR.DOCTOR_ID.eq(ref.LOGGED_DOCTOR.getDoctor_id())).execute();
                Notify.ConfirmationMessage("Account deleted Successfully");
                System.exit(0);
            } else {
                System.out.println("Failed to delete the file");
                Notify.ConfirmationMessage("Failed to delete the account");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onClearDatabase(ActionEvent actionEvent) {
        File file = new File("databases/"+ref.LOGGED_DOCTOR.getDoctor_database()+".h2.db");
        try {
            ref.databaseManagerInner.close();
            String str = file.getCanonicalPath();
            System.out.println(str);
            if(file.delete()) {
                System.out.println("File deleted successfully");
                Notify.ConfirmationMessage("Database cleared, Now all your students & data are gone");
                System.exit(0);
            } else {
                System.out.println("Failed to delete the file");
                Notify.ConfirmationMessage("Failed to delete the Database");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void projectileError(Control tx, String message){
        MenuItem menuItem = new MenuItem(message);
        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        ContextMenu contextValidator = new ContextMenu();
        contextValidator.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(menuItem);
        contextValidator.show(tx,Side.LEFT,0,0);
    }
}
