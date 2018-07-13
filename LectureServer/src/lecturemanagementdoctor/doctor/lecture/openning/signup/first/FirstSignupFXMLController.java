/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.openning.signup.first;

import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lecturemanagementdoctor.doctor.Database.pojos.Doctor;
import lecturemanagementdoctor.doctor.constant.Styles;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.animation.fade.Fade;
import lecturemanagementdoctor.doctor.animation.fade.FadeIn;
import lecturemanagementdoctor.doctor.animation.fade.FadeOut;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.openning.login.LoginFXMLController;
import lecturemanagementdoctor.doctor.lecture.openning.signup.second.SecSignupFXMLController;
import lecturemanagementdoctor.doctor.ref;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class FirstSignupFXMLController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private VBox FirstSignUpPane;

    @FXML
    private JFXPasswordField re_password;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;

    @FXML
    private Label LoginText;

    // ------------------------------------
    VBox LoginPane;

    private StackPane LoginStackPane;

    private Parent SecSignUp;

    //-----------tips----------------------
    // Context Menu for error messages
    final ContextMenu contextValidator = new ContextMenu();

    @FXML
    void NextAction(ActionEvent event) {

        if (!checkFields()){
            return;
        }

        if (!goNext()){
            return;
        }

        LoadSecSignUp();
        FadeOut fadeout = new FadeOut(FirstSignUpPane, Fade.FADE_DOWN);
        fadeout.start();
        fadeout.setAfterAnimationListener(() -> {
            LoginStackPane.getChildren().clear();
            LoginStackPane.getChildren().add(SecSignUp);
            FadeIn fadein = new FadeIn(SecSignUp, Fade.FADE_UP);
            fadein.start();
        });

    }

    private void projectileError(Control tx, String message) {
        MenuItem menuItem = new MenuItem(message);
        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(menuItem);
        contextValidator.show(tx,Side.LEFT,0,0);
    }

    @FXML
    void enterLogin(MouseEvent event) {
        LoginText.setTextFill(Color.web(Styles.colorPrimary));
    }

    @FXML
    void exitLogin(MouseEvent event) {
        LoginText.setTextFill(Color.web(Styles.colorGrey));
    }

    @FXML
    void LoginRelease(MouseEvent event) {
        LoadLogin();
        FadeOut fadeout = new FadeOut(FirstSignUpPane, Fade.FADE_UP);
        fadeout.start();
        fadeout.setAfterAnimationListener(() -> {
            LoginStackPane.getChildren().clear();
            LoginStackPane.getChildren().add(LoginPane);
            FadeIn fadein = new FadeIn(LoginPane, Fade.FADE_DOWN);
            fadein.start();
        });

    }

    private void LoadLogin() {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.LOGIN);
        LoginFXMLController controller = (LoginFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        LoginPane = (VBox) load.getParent();
    }

    public void LoadSecSignUp() {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.SEC_SIGNUP);
        SecSignupFXMLController controller = (SecSignupFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        SecSignUp = load.getParent();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirstSignUpPane.setStyle("-fx-background-color: rgba(150, 150, 150, 0.5); -fx-background-radius: 10;");
        contextValidator.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.setAutoHide(false);
    }

    public void setMainPane(StackPane mainPane) {
        this.LoginStackPane = mainPane;
    }

    private boolean checkFields(){
        if (username.getText().isEmpty()){
            username.setUnFocusColor(Color.RED);
            projectileError(username,"Please Type your name!");
            return false;
        }
        if (password.getText().isEmpty()){
            password.setUnFocusColor(Color.RED);
            projectileError(password,"Please Type a password!");
            return false;
        }
        if (re_password.getText().isEmpty()){
            re_password.setUnFocusColor(Color.RED);
            projectileError(re_password,"Please retype the password!");
            return false;
        }
        if (email.getText().isEmpty()){
            email.setUnFocusColor(Color.RED);
            projectileError(email,"Please type an E-Mail!");
            return false;
        }

        if (!password.getText().equals(re_password.getText())){
            projectileError(password,"Passwords are not matched!");
            return false;
        }
        return true;
    }

    private boolean goNext() {
        try {
            ref.LOGGED_DOCTOR = new Doctor();
            ref.LOGGED_DOCTOR.setDoctor_name(username.getText());
            ref.LOGGED_DOCTOR.setDoctor_password(password.getText());
            ref.LOGGED_DOCTOR.setDoctor_email(email.getText());
            return true;
        }catch (Exception ex){
            return false;
        }
    }

}
