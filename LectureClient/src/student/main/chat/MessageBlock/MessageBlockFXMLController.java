/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.main.chat.MessageBlock;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
    public static final String DOCTOR_IMG_PATH = "/res/doctor.png";
    public static final String STUDENT_IMG_PATH = "/res/student.png";
    private final String initialStyle = "-fx-border-radius : 10;  -fx-background-radius : 10; ";

    private final String MSG_GROUND_DOCTOR_COLOR = "#004BA6";
    private final String MSG_GROUND_STUDENT_COLOR = "#424242";

    private final int WIDTH = 300;
    private final int WARP_TXT = 243;

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

    private void isStudentMsg() {
        imgView.setImage(new Image(STUDENT_IMG_PATH));
        MsgRoot.setRight(imgView);
        BorderPane.setAlignment(imgView, Pos.TOP_RIGHT);
        BorderPane.setMargin(imgView, new Insets(5, 5, 5, 0));
        MsgRoot.setStyle(initialStyle + " -fx-background-color :" + MSG_GROUND_DOCTOR_COLOR + ";");
    }

    private void isDoctorMsg() {
        imgView.setImage(new Image(DOCTOR_IMG_PATH));
        MsgRoot.setLeft(imgView);
        BorderPane.setMargin(imgView, new Insets(5, 0, 5, 5));
        BorderPane.setAlignment(imgView, Pos.TOP_LEFT);
        MsgRoot.setStyle(initialStyle + " -fx-background-color :" + MSG_GROUND_STUDENT_COLOR + ";");
    }

}
