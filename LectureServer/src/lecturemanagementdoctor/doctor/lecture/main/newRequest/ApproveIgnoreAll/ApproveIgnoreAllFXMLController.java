/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.newRequest.ApproveIgnoreAll;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import network.clientsocket.Models.FromServerSignUpStatus;
import lecturemanagementdoctor.doctor.ref;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class ApproveIgnoreAllFXMLController implements Initializable {

    private Socket sendSocket;   // for replay 
    private VBox RequestBox;

    private StackPane numOfRequestPane;
    private Text SignUpCounterText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void approveAllAction(ActionEvent event) throws IOException {
        // send response
        // delete from list
        // set counter to zero 
        // saad#
        // add students to database

        SendRequsetStatus(true);
        DeleteAllStudent();
        setRequestCounterToZero();

        ref.requestList.clear();
    }

    @FXML
    void IgnoreAllAction(ActionEvent event) throws IOException {

        SendRequsetStatus(false);
        DeleteAllStudent();
        setRequestCounterToZero();
        ref.requestList.clear();

    }

    public Socket getSendSocket() {
        return sendSocket;
    }

    public void setSendSocket(Socket sendSocket) {
        this.sendSocket = sendSocket;
    }

    private void DeleteAllStudent() {
        ObservableList<Node> children = RequestBox.getChildren();
        HBox ApproveIgnoreAllBox = (HBox) children.get(0);
        children.clear();
        children.add(ApproveIgnoreAllBox);
    }

    private void setRequestCounterToZero() {
        numOfRequestPane.setVisible(false);
        SignUpCounterText.setText("0");
    }

    private void SendRequsetStatus(boolean status) {
        for (int i = 0; i < ref.requestList.size(); i++) {
            FromServerSignUpStatus statusObject = new FromServerSignUpStatus();
            VBox box = (VBox) RequestBox.getChildren().get(i);
            HBox textBox = (HBox) box.getChildren().get(0);
            Text usernameTxt = (Text) textBox.getChildren().get(0);

            // check database 
            if (status == true) {
                System.out.println(ref.requestList.get(i).getUsername());
                // if found in database 
             //   statusObject.setStatus(FromServerSignUpStatus.REJECT);
                // if not found 
             //   statusObject.setStatus(FromServerSignUpStatus.ACCPET);
            //  ref.server.SendTCP(statusObject, usernameTxt.getText());
             continue;
            }
            if (status) {
                // send Accept
                statusObject.setStatus(FromServerSignUpStatus.ACCPET);
            } else {
                // Send Reject
                statusObject.setStatus(FromServerSignUpStatus.REJECT);
            }
            ref.server.SendTCP(statusObject, usernameTxt.getText());
        }
    }

    public void setRequestBox(VBox RequestBox) {
        this.RequestBox = RequestBox;
    }

    public void SignUpCounterInfo(Rectangle SignUpCounterRect, StackPane numOfRequestPane, Text SignUpCounterText) {
        this.numOfRequestPane = numOfRequestPane;
        this.SignUpCounterText = SignUpCounterText;
    }

}
