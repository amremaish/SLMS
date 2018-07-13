/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.openning.signup.second;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.net.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lecturemanagementdoctor.doctor.Database.InnerDataBaseManager;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.records.DoctorRecord;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.constant.Styles;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.animation.fade.Fade;
import lecturemanagementdoctor.doctor.animation.fade.FadeIn;
import lecturemanagementdoctor.doctor.animation.fade.FadeOut;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.main.MainFXMLController;
import lecturemanagementdoctor.doctor.lecture.openning.signup.first.FirstSignupFXMLController;
import lecturemanagementdoctor.doctor.ref;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class SecSignupFXMLController implements Initializable {

    @FXML
    public JFXTextField courseName;

    @FXML
    public ToggleGroup gender;

    @FXML
    private JFXTextField phone;

    @FXML
    private VBox SecSignUpPane;

    @FXML
    private JFXTextField department;

    @FXML
    private Text BackText;

    //---------------------------------------
    private Parent firstSignUp;
    private StackPane LoginStackPane;

    //-----------tips----------------------
    // Context Menu for error messages
    final ContextMenu contextValidator = new ContextMenu();

    @FXML
    void SignUpAction(ActionEvent event) {

        if (!checkFields()){
            return;
        }

        if (!saveData()){
            return;
        }

        // load main
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.MAIN);
        MainFXMLController controller = (MainFXMLController) load.getController();
        LoginStackPane.getScene().setRoot(load.getParent());
        CustomStageStyle.undecoratedStageOptions(ref.MAIN_STAGE, controller.getParent());
    }

    private void projectileError(Control tx, String message) {
        MenuItem menuItem = new MenuItem(message);
        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(menuItem);
        contextValidator.show(tx,Side.LEFT,0,0);
    }

    @FXML
    void BackEnter(MouseEvent event) {
        BackText.setFill(Color.web(Styles.colorPrimary));
    }

    @FXML
    void BackExit(MouseEvent event) {
        BackText.setFill(Color.web(Styles.colorGrey));
    }

    @FXML
    void releaseBack(MouseEvent event) {
        LoadFirstSignUp();
        FadeOut fadeout = new FadeOut(SecSignUpPane, Fade.FADE_UP);
        fadeout.start();
        fadeout.setAfterAnimationListener(() -> {
            LoginStackPane.getChildren().clear();
            LoginStackPane.getChildren().add(firstSignUp);
            FadeIn fadein = new FadeIn(firstSignUp, Fade.FADE_DOWN);
            fadein.start();
        });
    }

    private void LoadFirstSignUp() {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.FIRST_SIGNUP);
        FirstSignupFXMLController controller = (FirstSignupFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        firstSignUp = load.getParent();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SecSignUpPane.setStyle("-fx-background-color: rgba(150, 150, 150, 0.5); -fx-background-radius: 10;");
        contextValidator.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
    }

    public void setMainPane(StackPane mainPane) {
        this.LoginStackPane = mainPane;
    }

    private boolean checkFields(){
        if (phone.getText().isEmpty()){
            phone.setUnFocusColor(Color.RED);
            projectileError(phone,"Please type a phone!");
            return false;
        }
        if (department.getText().isEmpty() ){
            projectileError(department,"Please type a department!");
            return false;
        }
        if (courseName.getText().isEmpty()){
            courseName.setUnFocusColor(Color.RED);
            projectileError(courseName,"Please type the Course Name!");
            return false;
        }

        JFXRadioButton selectedRadioButton = (JFXRadioButton) gender.getSelectedToggle();
        if (selectedRadioButton == null){
            projectileError(department,"Please select the gender!");
            return false;
        }
        return true;
    }

    private boolean saveData(){
        try {
            JFXRadioButton selectedRadioButton = (JFXRadioButton) gender.getSelectedToggle();
            ref.LOGGED_DOCTOR.setDoctor_phone(phone.getText());
            ref.LOGGED_DOCTOR.setDoctor_department(department.getText());
            ref.LOGGED_DOCTOR.setDoctor_gender(selectedRadioButton.getId());
            ref.LOGGED_DOCTOR.setDoctor_course(courseName.getText());
            ref.LOGGED_DOCTOR.setDoctor_database(ref.LOGGED_DOCTOR.getDoctor_name()+"_"+ref.LOGGED_DOCTOR.getDoctor_course());
            DoctorRecord doctorRecord = ref.databaseManagerOuter.insert(ref.LOGGED_DOCTOR).into(lecturemanagementdoctor.doctor.Database.doctordb.tables.Doctor.DOCTOR);
            ref.LOGGED_DOCTOR.setDoctor_id(doctorRecord.getDoctorId());

            ref.databaseManagerInner = new InnerDataBaseManager(ref.LOGGED_DOCTOR.doctor_database);

            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
