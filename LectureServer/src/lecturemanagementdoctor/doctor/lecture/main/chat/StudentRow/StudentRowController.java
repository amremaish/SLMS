package lecturemanagementdoctor.doctor.lecture.main.chat.StudentRow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Pair;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.main.chat.MessageBlock.MessageBlockFXMLController;
import lecturemanagementdoctor.doctor.ref;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class StudentRowController implements Initializable {

    @FXML
    private BorderPane StudentRowPane;

    @FXML
    private Circle MsgNotifierCircle;
    @FXML
    private Text usernameTxt;

    public static final int WIDTH = 260, HEIGHT = 45;

    private final String STANDARD_PANE_COLOR = "#F2F2F2";
    private final String HOVERED_PANE_COLOR = "#E6E6E6";

    @FXML
    void EnteredRow(MouseEvent event) {
        StudentRowPane.setStyle("-fx-background-color :" + HOVERED_PANE_COLOR + " ;");
    }

    @FXML
    void clickAction(MouseEvent event) {
        ref.usernameTxtMsg.setText(usernameTxt.getText());
        LoadMessages();
    }

    private void LoadMessages() {
        ref.MSG_V_BOX.getChildren().clear();
        ArrayList<Pair<String, Integer>> list = ref.Messages.get(usernameTxt.getText());
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int Type = list.get(i).getSecond();
            String msg = list.get(i).getFirst();
            LoadMessage(Type, msg);
        }

    }

    private void LoadMessage(int Type, String msg) {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.MESSAGE_BLOCK);
        MessageBlockFXMLController controller = (MessageBlockFXMLController) load.getController();
        if (Type == ref.DOCTOR) {
            controller.setDoctorMsg(msg);
        } else {
            controller.setStudentMsg(msg);
        }
        addMessageToBox(load.getParent());

    }

    private void addMessageToBox(Parent messageBox) {
        Platform.runLater(() -> {
            HBox rightBox = new HBox();
            rightBox.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            rightBox.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            rightBox.setAlignment(Pos.CENTER_RIGHT);
            rightBox.getChildren().add(messageBox);
            ref.MSG_V_BOX.getChildren().add(rightBox);
        });
    }

    @FXML
    void ExitRow(MouseEvent event) {
        StudentRowPane.setStyle("-fx-background-color:" + STANDARD_PANE_COLOR + " ;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StudentRowPane.setPrefSize(WIDTH, HEIGHT);
    }

    public BorderPane getStudentRowPane() {
        return StudentRowPane;
    }

    public Circle getMsgNotifierCircle() {
        return MsgNotifierCircle;
    }

    public String getUsernameText() {
        return usernameTxt.getText();
    }

    public void setUsernameText(String txt) {
        usernameTxt.setText(txt);
    }

}
