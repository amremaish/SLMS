/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.newRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.UNDECORATED;

import lecturemanagementdoctor.doctor.Database.pojos.Student;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.main.newRequest.StudentInfoWindow.StudentInfoWindowController;

import lecturemanagementdoctor.doctor.ref;
import network.clientsocket.Models.FromServerSignUpStatus;
import network.serversocket.Models.FromClientSignUp;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class RequestFXMLController implements Initializable {

    @FXML
    private Text usernameText;

    private VBox RequestBox;

    private FromClientSignUp signupInfo;

    private Rectangle SignUpCounterRect;

    private StackPane numOfRequestPane;

    private Text SignUpCounterText;

    @FXML
    void approveButtonAction(ActionEvent event) throws IOException {
        // saad#
        // store to database
        Student student = new Student(signupInfo.getUsername(), signupInfo.getEmail(), signupInfo.getPassowrd(), signupInfo.getDepartment(), signupInfo.getPhoneNumber(), signupInfo.getGender());
        ref.databaseManagerInner.insert(student);

        // send accept to client
        FromServerSignUpStatus status = new FromServerSignUpStatus();
        status.setStatus(FromServerSignUpStatus.ACCPET);
        ref.server.SendTCP(status, signupInfo.getUsername());
        DeleteThisFromBoxStudent();
        removeFromRequestList();
    }

    @FXML
    void ignoreButtonAction(ActionEvent event) {
        // send reject to client
        FromServerSignUpStatus status = new FromServerSignUpStatus();
        status.setStatus(FromServerSignUpStatus.REJECT);
        ref.server.SendTCP(status, signupInfo.getUsername());
        // remove student from Vbox 
        DeleteThisFromBoxStudent();
        removeFromRequestList();
    }

    @FXML
    void infoButtonAction(ActionEvent event) {
        // show new stage  with information
        Stage stage = new Stage();
        stage.initStyle(UNDECORATED);
        stage.setResizable(false);
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.STUDENT_INFO);
        StudentInfoWindowController controller = (StudentInfoWindowController) load.getController();
        controller.setDepartement(signupInfo.getDepartment());
        controller.setEmail(signupInfo.getEmail());
        controller.setGender(signupInfo.getGender());
        controller.setUsername(signupInfo.getUsername());
        controller.setPhone(signupInfo.getPhoneNumber());
        stage.setScene(new Scene(load.getParent()));
        CustomStageStyle.DragStageOption(stage, controller.getBorderBox());
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void minusOneRequestCounter() {
        int minusOne = Integer.parseInt(SignUpCounterText.getText()) - 1;
        if (minusOne == 0) {
            numOfRequestPane.setVisible(false);
            SignUpCounterText.setText("0");
            return;
        }
        SignUpCounterText.setText(minusOne + "");

        SignUpCounterRect.setWidth(SignUpCounterText.getText().length() * 4 - 4 + 14);
    }

    private void DeleteThisFromBoxStudent() {
        ObservableList<Node> children = RequestBox.getChildren();

        for (int i = 0; i < children.size(); i++) {
            VBox subVbox = null;
            try {
                subVbox = (VBox) children.get(i);
            } catch (Exception x) {
                continue;
            }
            HBox subHbox = (HBox) subVbox.getChildren().get(0);
            Text text = (Text) subHbox.getChildren().get(0);
            if (text.getText().equals(usernameText.getText())) {
                children.remove(i);
                minusOneRequestCounter(); // -----> minus counter by 1 
            }
        }
    }

    public void setRequestBox(VBox RequestBox) {
        this.RequestBox = RequestBox;
    }

    public void setSignupInfo(FromClientSignUp signupInfo) {
        this.signupInfo = signupInfo;
        usernameText.setText(signupInfo.getUsername());
    }

    public void SignUpCounterInfo(Rectangle SignUpCounterRect, StackPane numOfRequestPane, Text SignUpCounterText) {
        this.SignUpCounterRect = SignUpCounterRect;
        this.numOfRequestPane = numOfRequestPane;
        this.SignUpCounterText = SignUpCounterText;
        // increment 1 
        int plusOne = Integer.parseInt(SignUpCounterText.getText()) + 1;
        SignUpCounterText.setText(plusOne + "");
        numOfRequestPane.setVisible(true);
    }

    private void removeFromRequestList() {
        // remove them from list 
        for (int i = 0; i < ref.requestList.size(); i++) {
            if (ref.requestList.get(i).getUsername().equals(usernameText.getText())) {
                ref.requestList.remove(i);
                break;
            }
        }
    }

}
