/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.chat.MessageBlock;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lecturemanagementdoctor.doctor.constant.Path;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class MessageBlockFXMLController implements Initializable {

    @FXML
    private ImageView imgView;

    @FXML
    private Text msgTxt;

    @FXML
    private BorderPane MsgRoot;
    //---------------------------------------

    private final String initialStyle = "-fx-border-radius : 10;  -fx-background-radius : 10; ";

    private final String MSG_GROUND_DOCTOR_COLOR = "#004BA6";
    private final String MSG_GROUND_STUDENT_COLOR = "#424242";

    private final int WIDTH = 220;
    private final int WARP_TXT = 175;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MsgRoot.setPrefWidth(WIDTH);
        msgTxt.setWrappingWidth(WARP_TXT);
        imgView.setFitWidth(20);
        imgView.setFitHeight(20);
        msgTxt.setTextAlignment(TextAlignment.LEFT);
    }

    public void setDoctorMsg(String msg) {
        msgTxt.setText(msg);
        MsgRoot.setLeft(null);
        MsgRoot.setRight(null);
        isDoctorMsg();
    }

    public void setStudentMsg(String msg) {
        msgTxt.setText(msg);
        MsgRoot.setLeft(null);
        MsgRoot.setRight(null);
        isStudentMsg();
    }

    private void isDoctorMsg() {
        imgView.setImage(new Image(Path.DOCTOR_IMG_PATH));
        MsgRoot.setRight(imgView);
        BorderPane.setAlignment(imgView, Pos.TOP_RIGHT);
        BorderPane.setMargin(imgView, new Insets(5, 5, 5, 0));
        MsgRoot.setStyle(initialStyle + " -fx-background-color :" + MSG_GROUND_DOCTOR_COLOR + ";");
       MsgRoot.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }

    private void isStudentMsg() {
        imgView.setImage(new Image(Path.STUDENT_IMG_PATH));
        MsgRoot.setLeft(imgView);
        BorderPane.setMargin(imgView, new Insets(5, 0, 5, 5));
        BorderPane.setAlignment(imgView, Pos.TOP_LEFT);
        MsgRoot.setStyle(initialStyle + " -fx-background-color :" + MSG_GROUND_STUDENT_COLOR + ";");
         MsgRoot.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }

}
