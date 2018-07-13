/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.opening.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import student.Utility.LoadFXML;
import student.Utility.Stage.undecoratedStage.StageStyle;
import student.animation.fade.Fade;
import student.animation.fade.FadeIn;
import student.animation.fade.FadeOut;
import student.constant.FXMLPath;
import student.constant.Styles;
import student.main.MainFXMLController;
import network.clientsocket.Models.FromServerSignIn;
import network.serversocket.Models.FromClientLogin;
import student.Utility.Notify;
import student.opening.signup.first.FirstSignupFXMLController;
import student.ref;

/**
 * FXML Controller class
 *
 * @author Saad
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label SignUpText;



    @FXML
    private VBox LoginPane;

    @FXML
    private JFXTextField username;
    // ------------------------ 
    private Parent firstSignUp;

    private StackPane LoginStackPane;

    @FXML
    void LoginAction(ActionEvent event) {
        // load main
        FromClientLogin login = new FromClientLogin();
        login.setUsername(username.getText());
        login.setPassword(password.getText());
        ref.client.SendTCP(login);

        ref.client.setFromServerSignInListener((FromServerSignIn signin) -> {
            if (signin.getStatus() == FromServerSignIn.ACCEPT) {
                LoadFXML load = new LoadFXML();
                load.LoadFXML(FXMLPath.MAIN);
                MainFXMLController controller = (MainFXMLController) load.getController();
                LoginStackPane.getScene().setRoot(load.getParent());
                StageStyle.undecoratedStageOptions(ref.MAIN_STAGE, controller.getBorder());
                ref.username = username.getText();
            } else if (signin.getStatus() == FromServerSignIn.REJECT) {
                Notify.errorMessage("username or password is incorrect.");

            }
        });

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

}
