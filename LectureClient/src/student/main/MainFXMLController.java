/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.main;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import student.Utility.Effect;
import student.Utility.Stage.undecoratedStage.StageStyle;
import student.animation.openCloseList.OpenCloseList;
import student.constant.Colors;
import student.constant.Path;
import student.constant.Styles;
import student.opening.OpenningController;
import student.ref;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import network.clientsocket.Listener.FromServerMessageListener;
import network.clientsocket.Listener.FromServerQuizListener;
import network.clientsocket.Models.FromServerQuiz;
import network.shared.models.Message;
import student.Utility.LoadFXML;
import student.Utility.Pair;
import student.constant.FXMLPath;
import student.main.chat.MessageBlock.MessageBlockFXMLController;
import student.main.quiz.QuizFXMLController;

/**
 * FXML Controller class
 *
 * @author Saad
 */
public class MainFXMLController implements Initializable {

    @FXML
    private VBox list_box;

    @FXML
    private ImageView min;

    @FXML
    private ImageView arrow;

    @FXML
    private ImageView max;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView close;

    @FXML
    private ImageView setting;

    @FXML
    private BorderPane Border;

    @FXML
    private BorderPane ListBordePane;
    @FXML
    private BorderPane mainRoot;

    //------------------------------------------
    private OpenCloseList ListAnim;

    //------------------------------------------
    public static Parent[] Sessions = new Parent[3];

    @FXML
    void requestAction(ActionEvent event) {

    }

    @FXML
    void LectureClick(ActionEvent event) {
        setSession(0, "waiting/FXMLWaiting.fxml");
    }

    @FXML
    void QuizClick(ActionEvent event) {
        setSession(1, "quiz/quizFXML.fxml");
    }

    @FXML
    void chatClick(ActionEvent event) {
        setSession(2, "chat/chatFXML.fxml");
        // load messages 
        if (ref.MSG_V_BOX != null) {
            ref.MSG_V_BOX.getChildren().clear();
        }
        for (int i = 0; i < ref.Messages.size(); i++) {
            LoadMessage(ref.Messages.get(i).getSecond(), ref.Messages.get(i).getFirst());
        }
    }

    @FXML
    void msgAction(ActionEvent event) {

    }

    @FXML
    void arrowAction(ActionEvent event) {

        if (ListAnim.isClosed()) {
            ListAnim.StartOpenList();
        } else {
            ListAnim.StartCloseList();
        }
        ListAnim.setAfterAnimationListener(() -> {

            if (ListAnim.isClosed()) {
                ref.CLOSED_LIST = false;
                arrow.setImage(new Image(Path.close_list));
            } else {
                ref.CLOSED_LIST = true;
                System.out.println(ref.CLOSED_LIST);
                arrow.setImage(new Image(Path.open_list));
            }

            if (ref.CLOSED_LIST && ref.MSG_V_BOX != null) {
                ref.MSG_V_BOX.setPrefWidth(ref.MAIN_STAGE.getWidth() - 270);
            } else if (ref.MSG_V_BOX != null && !ref.CLOSED_LIST) {
                ref.MSG_V_BOX.setPrefWidth(ref.MAIN_STAGE.getWidth() - 388);
            }
        });

    }

    @FXML
    void profileAction(ActionEvent event) {

    }

    @FXML
    void settingAction(ActionEvent event) {
        ContextMenu contextValidator = new ContextMenu();
//        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(new MenuItem("Logout"));
        contextValidator.show(setting,Side.BOTTOM,0,0);
        contextValidator.setOnAction(e -> {
            logout();
        });

    }

    private void logout(){
        ref.nullize();

        // load main
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.OPENING);
        OpenningController controller = (OpenningController) load.getController();
        mainRoot.getScene().setRoot(load.getParent());
        StageStyle.undecoratedStageOptions(ref.MAIN_STAGE, controller.getBorder());
    }

    //--------------------------------------------
    @FXML
    void iconEnter(MouseEvent event) {
        JFXButton src = (JFXButton) event.getSource();
        ImageView graphic = (ImageView) src.getGraphic();
        Effect.setImageColor(graphic, Color.web(Styles.colorIconHover));
    }

    @FXML
    void iconExit(MouseEvent event) {
        JFXButton src = (JFXButton) event.getSource();
        ImageView graphic = (ImageView) src.getGraphic();
        Effect.setImageColor(graphic, Color.web(Styles.colorWhite));
    }

    @FXML
    void arrowEntered(MouseEvent event) {
        Effect.setImageColor(arrow, Color.web(Styles.colorArrowHover));
    }

    @FXML
    void arrowExit(MouseEvent event) {
        Effect.setImageColor(arrow, Color.web(Styles.colorPrimary));
    }

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
        Stage stage = (Stage) min.getScene().getWindow();
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

    @FXML
    void closeEnter(MouseEvent event) {

    }

    @FXML
    void closeExit(MouseEvent event) {

    }

    @FXML
    void onPlay(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colorizeAllPics();

        ObservableList<Node> children = list_box.getChildren();
        JFXButton[] buttonList = new JFXButton[children.size()];
        for (int i = 0; i < children.size(); i++) {
            buttonList[i] = (JFXButton) children.get(i);
        }
        ListAnim = new OpenCloseList(buttonList, ListBordePane);
        StartMessageListener();
        StartQuizListener();
        ref.MAIN_STAGE.setResizable(false);
    }

    public Parent getBorder() {
        return mainRoot;
    }

    private void colorizeAllPics() {
        Effect.setImageColor(max, Colors.EXIT_COLOR);
        Effect.setImageColor(min, Colors.EXIT_COLOR);
        Effect.setImageColor(profile, Color.web(Styles.colorWhite));
        Effect.setImageColor(setting, Color.web(Styles.colorWhite));
        Effect.setImageColor(close, Color.web(Styles.colorClose));
    }

    public void setSession(int index, String name) {
        if (Sessions[index] == null) {
            try {
                Sessions[index] = FXMLLoader.load(getClass().getResource(Path.sessions_path + name));
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        mainRoot.setCenter(null);
        mainRoot.setCenter(Sessions[index]);

    }

    private void StartMessageListener() {
        ref.client.setServerMessageListener((Message Message) -> {
            ref.Messages.add(new Pair<>(Message.getMessage(), ref.DOCTOR));
            if (ref.MSG_V_BOX != null) {
                LoadMessage(ref.DOCTOR, Message.getMessage());
            }
        });
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

    private void StartQuizListener() {
        ref.client.setFromServerQuizListener((FromServerQuiz quiz) -> {
            Platform.runLater(() -> {
                LoadFXML load = new LoadFXML();
                load.LoadFXML(FXMLPath.QUIZ_PANEL);
                 QuizFXMLController controller = (QuizFXMLController) load.getController();
                 controller.setQuiz(quiz);
                Sessions[1] = load.getParent();
                setSession(1, "quiz/quizFXML.fxml");
                
            });
        });
    }

}
