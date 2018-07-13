/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.openning.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lecturemanagementdoctor.doctor.Database.InnerDataBaseManager;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.records.DoctorRecord;
import lecturemanagementdoctor.doctor.Database.pojos.Doctor;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.animation.fade.Fade;
import lecturemanagementdoctor.doctor.animation.fade.FadeIn;
import lecturemanagementdoctor.doctor.animation.fade.FadeOut;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.constant.Styles;
import lecturemanagementdoctor.doctor.lecture.main.MainFXMLController;
import lecturemanagementdoctor.doctor.lecture.openning.signup.first.FirstSignupFXMLController;
import lecturemanagementdoctor.doctor.ref;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class LoginFXMLController implements Initializable {

    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;

    @FXML
    private VBox LoginPane;
    @FXML
    private Label SignUpText;
    @FXML
    private Label forgotPassword;
    // ------------------------ 
    private Parent firstSignUp;

    private StackPane LoginStackPane;

    //-----------tips----------------------
    // Context Menu for error messages
    final ContextMenu contextValidator = new ContextMenu();

    @FXML
    void LoginAction(ActionEvent event) {

        if (!checkFields()){
            return;
        }

        if (!loadData()){
            return;
        }

        // load main
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.MAIN);
        MainFXMLController controller = (MainFXMLController) load.getController();
        LoginStackPane.getScene().setRoot(load.getParent());
        CustomStageStyle.undecoratedStageOptions(ref.MAIN_STAGE, controller.getParent());
    }

    private void projectileError(Control tx,String message){
        MenuItem menuItem = new MenuItem(message);
        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(menuItem);
        contextValidator.show(tx,Side.LEFT,0,0);
    }

    @FXML
    void SignUpEnter(MouseEvent event) {
        SignUpText.setTextFill(Color.web(Styles.colorSecondary));
    }

    @FXML
    void SignUpExit(MouseEvent event) {
        SignUpText.setTextFill(Color.web(Styles.colorPrimary));
    }

    @FXML
    void forgotPasswordEnter(MouseEvent event) {
        forgotPassword.setTextFill(Color.web(Styles.colorSecondary));
    }

    @FXML
    void forgotPasswordExit(MouseEvent event) {
        forgotPassword.setTextFill(Color.web(Styles.colorGrey));
    }

    @FXML
    void SignUpRelease(MouseEvent event) {
        LoadFirstSignUp();
        FadeOut fadeout = new FadeOut(LoginPane, Fade.FADE_DOWN);
        fadeout.start();
        fadeout.setAfterAnimationListener(() -> {
            LoginStackPane.getChildren().clear();
            LoginStackPane.getChildren().add(firstSignUp);
            FadeIn fadein = new FadeIn(firstSignUp, Fade.FADE_UP);
            fadein.start();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginPane.setStyle("-fx-background-color: rgba(150, 150, 150, 0.5); -fx-background-radius: 10;");
        contextValidator.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
    }

    private void LoadFirstSignUp() {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.FIRST_SIGNUP);
        FirstSignupFXMLController controller = (FirstSignupFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        firstSignUp = load.getParent();
    }

    public void setMainPane(StackPane mainPane) {
        this.LoginStackPane = mainPane;
    }

    private boolean checkFields(){
        if (username.getText().isEmpty()){
            username.setUnFocusColor(Color.RED);
            projectileError(username,"Please Type your username!");
            return false;
        }

        if (password.getText().isEmpty()){
            password.setUnFocusColor(Color.RED);
            projectileError(password,"Please Type your password!");
            return false;
        }
        return true;
    }

    private boolean loadData(){
        if (ref.CURRENT_COURSE == null){
            projectileError(username,"Please, choose a course");
            return false;
        }
        DoctorRecord doctorRecord = ref.databaseManagerOuter.checkUsernameAndPassword(username.getText(),password.getText());
        if (doctorRecord != null){
            if (!doctorRecord.getDoctorCourse().equals(ref.CURRENT_COURSE)){
                projectileError(username,"User mismatched with course");
                return false;
            }
            ref.LOGGED_DOCTOR = new Doctor(doctorRecord.getDoctorId(),
                    doctorRecord.getDoctorName(),
                    doctorRecord.getDoctorPassword(),
                    doctorRecord.getDoctorEmail(),
                    doctorRecord.getDoctorGender(),
                    doctorRecord.getDoctorCourse(),
                    doctorRecord.getDoctorPhone(),
                    doctorRecord.getDoctorDepartment(),
                    doctorRecord.getDoctorDatabase());

            ref.databaseManagerInner = new InnerDataBaseManager(ref.LOGGED_DOCTOR.doctor_database);

            return true;
        }
        projectileError(username,"Invalid Username or Password");
        return false;
    }
}
