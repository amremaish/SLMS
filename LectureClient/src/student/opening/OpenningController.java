/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.opening;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import student.Utility.Effect;
import student.Utility.LoadFXML;
import student.constant.Colors;
import student.constant.FXMLPath;
import network.clientsocket.ClientSocket;
import network.clientsocket.Models.FromServerSignUpStatus;
import student.Utility.Notify;
import student.opening.login.LoginFXMLController;
import student.ref;

/**
 *
 * @author Saad Alenany
 */
public class OpenningController implements Initializable {

    @FXML
    private ImageView slideImageView;

    @FXML
    private BorderPane border;

    @FXML
    private ImageView min;

    @FXML
    private ImageView max;

    @FXML
    private ImageView close;

    @FXML
    private BorderPane mainPane;

    @FXML
    private StackPane LoginStackPane;

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void maxAction(ActionEvent event) {
        Stage stage = (Stage) max.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    @FXML
    void minAction(ActionEvent event) {
        Stage stage = (Stage) max.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void minEnter(MouseEvent event) {
        Effect.setImageColor(min, Colors.ENTER_COLOR);
    }

    @FXML
    void minExit(MouseEvent event) {
        Effect.setImageColor(min, Colors.EXIT_COLOR);
    }

    @FXML
    void maxEnter(MouseEvent event) {
        Effect.setImageColor(max, Colors.ENTER_COLOR);
    }

    @FXML
    void maxExit(MouseEvent event) {
        Effect.setImageColor(max, Colors.EXIT_COLOR);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Effect.setImageColor(min, Colors.EXIT_COLOR);
        Effect.setImageColor(max, Colors.EXIT_COLOR);
        // ---------------------------------------------
        SetImage();
        ref.Messages = new ArrayList<>();
        // load login 
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.LOGIN);
        LoginFXMLController controller = (LoginFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        VBox b = (VBox) load.getParent();
        LoginStackPane.getChildren().add(b);
        boolean StartServer = StartServer();
        if (!StartServer) {
            Notify.errorMessageSameThread("Please wait for opening server !");
            System.exit(0);
        }

        StartRecievingSignUp();
    }

    private void SetImage() {
        // slider
        slideImageView.setFitHeight(505);
        slideImageView.setFitWidth(677);
        slideImageView.setImage(new Image("/res/lectureStudent.png"));

    }

    private boolean StartServer() {
        try {
            ref.client = new ClientSocket();
            ref.client.Start();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public BorderPane getBorder() {
        return border;
    }

    private void StartRecievingSignUp() {
        ref.client.setFromServerSignUpStatusListener((FromServerSignUpStatus status) -> {
            System.out.println("Status : " + status.getStatus());
        });
    }

}
