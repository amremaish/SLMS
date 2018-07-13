/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.main.chat;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import student.ref;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.paint.Color;
import network.shared.models.Message;
import student.Utility.LoadFXML;
import student.Utility.Pair;
import student.constant.FXMLPath;
import student.main.chat.MessageBlock.MessageBlockFXMLController;

/**
 *
 * @author Saad Alenany
 */
public class ChatFXMLController implements Initializable {

    @FXML
    private ScrollPane scroll_box;

    @FXML
    private VBox chat_box;

    @FXML
    public JFXTextField messageField;

    @FXML
    void SendActionButton(ActionEvent event) {
        if (messageField.getText().trim().isEmpty()) {
            return;
        }
        LoadMessage(ref.STUDENT, messageField.getText());
        ref.Messages.add(new Pair<>(messageField.getText(), ref.STUDENT));
        Message msg = new Message();
        msg.setStudent_username(ref.username);
        msg.setMessage(messageField.getText());
        // send to server
        ref.client.SendTCP(msg);
        messageField.setText("");
    }

    private void LoadMessage(int Type, String msg) {
        Platform.runLater(() -> {
            LoadFXML load = new LoadFXML();
            load.LoadFXML(FXMLPath.MESSAGE_BLOCK);
            MessageBlockFXMLController controller = (MessageBlockFXMLController) load.getController();
            if (Type == ref.DOCTOR) {
                controller.setDoctorMsg(msg);
                addMessageToBox(load.getParent(), Pos.CENTER_LEFT);
            } else {
                controller.setStudentMsg(msg);
                addMessageToBox(load.getParent(), Pos.CENTER_RIGHT);
            }

        });
    }

    private void addMessageToBox(Parent messageBox, Pos Pos) {
        HBox rightBox = new HBox();
        rightBox.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        rightBox.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        rightBox.setAlignment(Pos);
        rightBox.getChildren().add(messageBox);
        ref.MSG_V_BOX.getChildren().add(rightBox);

    }

    @FXML
    void MouseEnterButtons(MouseEvent event) {

    }

    @FXML
    void MouseExitButtons(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ref.MSG_V_BOX = chat_box;
        scroll_box.setBackground(
                new Background(new BackgroundFill(Color.TRANSPARENT, null, null))
        );
        resizeMsgs();
    }

    private void resizeMsgs() {
        chat_box.setPrefWidth(715);
        chat_box.setPrefHeight(450);

        ref.MAIN_STAGE.widthProperty().addListener((obs, oldVal, newVal) -> {
            chat_box.setPrefWidth(scroll_box.getWidth() - 5);

        });

        ref.MAIN_STAGE.heightProperty().addListener((obs, oldVal, newVal) -> {
            chat_box.setPrefHeight(scroll_box.getHeight() - 5);
        });
    }
}
