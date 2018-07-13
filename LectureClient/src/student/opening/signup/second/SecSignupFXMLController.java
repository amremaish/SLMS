/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.opening.signup.second;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import student.Utility.LoadFXML;
import student.animation.fade.Fade;
import student.animation.fade.FadeIn;
import student.animation.fade.FadeOut;
import student.constant.FXMLPath;
import student.constant.Styles;
import network.serversocket.Models.FromClientSignUp;

import student.opening.login.LoginFXMLController;
import student.opening.signup.first.FirstSignupFXMLController;
import student.ref;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class SecSignupFXMLController implements Initializable {

    @FXML
    public JFXRadioButton male;

    @FXML
    public JFXRadioButton female;

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
    private FromClientSignUp info;
    // Context Menu for error messages
    final ContextMenu contextValidator = new ContextMenu();

    @FXML
    void SignUpAction(ActionEvent event) {
        if (!checkFields()) {
            return;
        }
        info.setDepartment(department.getText());
        info.setPhoneNumber(phone.getText());
        if (male.isSelected()) {
            info.setGender("male");
        } else {
            info.setGender("female");
        }
        ref.client.SendTCP(info);
        loadLogin();
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

    private void loadLogin(){
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.LOGIN);
        LoginFXMLController controller = (LoginFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        firstSignUp = load.getParent();
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
    }

    public void setMainPane(StackPane mainPane) {
        this.LoginStackPane = mainPane;
    }

    public void setSignUpInfo(FromClientSignUp info) {

        this.info = info;
    }

    private boolean checkFields() {
        if (phone.getText().isEmpty()) {
            phone.setUnFocusColor(Color.RED);
            projectileError(phone, "Please Type your name!");
            return false;
        }

        if (department.getText().isEmpty()) {
            department.setUnFocusColor(Color.RED);
            projectileError(department, "Please retype the password!");
            return false;
        }

        return true;
    }

    private void projectileError(Control tx, String message) {
        MenuItem menuItem = new MenuItem(message);
        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(menuItem);
        contextValidator.show(tx, Side.LEFT, 0, 0);
    }
}
