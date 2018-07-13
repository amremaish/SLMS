/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.chat;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Notify;
import lecturemanagementdoctor.doctor.Utility.Pair;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.main.chat.MessageBlock.MessageBlockFXMLController;
import lecturemanagementdoctor.doctor.lecture.main.chat.StudentRow.StudentRowController;
import lecturemanagementdoctor.doctor.ref;

import network.shared.models.Message;

/**
 *
 * @author Saad Alenany
 */
public class ChatFXMLController implements Initializable {

    @FXML
    private HBox SendPane;

    @FXML
    private Text usernameTxtMsg;

    @FXML
    private StackPane MsgPane;

    @FXML
    private ScrollPane ScrollList;

    @FXML
    private JFXTextField msgTxt;

    @FXML
    private VBox msgVBox;

    @FXML
    private VBox student_contacts;

    @FXML
    private ScrollPane ScrollPaneContacts;

    @FXML
    void PressEnter(ActionEvent event) {

    }

    @FXML
    void SendActionButton(ActionEvent event) {
        if (msgTxt.getText().trim().isEmpty()) {
            return;
        }
        if (usernameTxtMsg.getText().equals("Nobody")) {
            Notify.errorMessage("Select student to send message .");
            return;
        }
        // send TCP 
        Message msg = new Message();
        msg.setMessage(msgTxt.getText());
        boolean SendTCP = ref.server.SendTCP(msg, usernameTxtMsg.getText());
        if (!SendTCP) {
            Notify.errorMessage("This student is disconnected");
            return;
        }
        //--------------------------------
        // load MESSAGE BLOCK
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.MESSAGE_BLOCK);
        MessageBlockFXMLController controller = (MessageBlockFXMLController) load.getController();
        controller.setDoctorMsg(msgTxt.getText());
        msgVBox.getChildren().add(load.getParent());

        // add message to list 
        if (ref.Messages.get(usernameTxtMsg.getText()) == null) {
            ref.Messages.put(usernameTxtMsg.getText(), new ArrayList<>());
        }
        ref.Messages.get(usernameTxtMsg.getText()).add(new Pair<>(usernameTxtMsg.getText(), ref.DOCTOR));
        msgTxt.setText("");

    }

    @FXML
    void MouseEnterButtons(MouseEvent event) {

    }

    @FXML
    void MouseExitButtons(MouseEvent event) {

    }

    @FXML
    void CloseAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ref.MSG_V_BOX = msgVBox;
        ScrollPaneContacts.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        ref.usernameTxtMsg = usernameTxtMsg;
        ScrollList.setStyle(" -fx-background-insets: 0, 0, 0, 0; ");
        LoadContacts();
    }

    private void LoadContacts() {
        if (ref.student_contacts != null) {
            return;
        }
        for (int i = 0; i < ref.OnlineStudents.size(); i++) {
            addRowContact(ref.OnlineStudents.get(i));
        }
        ref.student_contacts = student_contacts;

    }

    private void addRowContact(String username) {
        Platform.runLater(() -> {
            LoadFXML load = new LoadFXML();
            load.LoadFXML(FXMLPath.STUDENT_ROW);
            StudentRowController controller = (StudentRowController) load.getController();
            controller.setUsernameText(username);
            ref.student_contacts.getChildren().add(load.getParent());
        });
    }

}
