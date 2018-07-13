/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lecturemanagementdoctor.doctor.Database.OuterDataBaseManager;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Lecture;
import lecturemanagementdoctor.doctor.Database.pojos.Student;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.constant.Styles;
import lecturemanagementdoctor.doctor.Utility.Effect;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.animation.openCloseList.OpenCloseList;
import lecturemanagementdoctor.doctor.constant.Colors;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.constant.Path;
import lecturemanagementdoctor.doctor.lecture.main.newRequest.ApproveIgnoreAll.ApproveIgnoreAllFXMLController;
import lecturemanagementdoctor.doctor.lecture.main.newRequest.RequestFXMLController;

import lecturemanagementdoctor.doctor.lecture.main.stat.StudentAbsence;
import lecturemanagementdoctor.doctor.lecture.openning.OpenningController;
import lecturemanagementdoctor.doctor.lecture.openning.login.LoginFXMLController;
import lecturemanagementdoctor.doctor.ref;
import network.serversocket.Models.FromClientSignUp;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class MainFXMLController implements Initializable {

    @FXML
    public ScrollPane dynamicScroller;

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

    private final ContextMenu contextValidator = new ContextMenu();
    //------------------------------------------
    // sign up request 
    @FXML
    private Menu menu_request;
    @FXML
    private VBox requestBox;
    @FXML
    private Rectangle SignUpCounterRect;

    @FXML
    private StackPane numOfRequestPane;

    @FXML
    private Text SignUpCounterText;

    //------------------------------------------
    private OpenCloseList ListAnim;
    private ImageView request;

    public static Pane[] Sessions = new Pane[5];

    public static Stage profileStage;

    @FXML
    void LectureClick(ActionEvent event) {
        setSession(0, "lecture/create/createLecture_fragment.fxml");
    }

    @FXML
    void statClick(ActionEvent event) {
        setSession(1, "stat/statFXML.fxml");
    }

    @FXML
    void QuizClick(ActionEvent event) {
        setSession(2, "quiz/quizFXML.fxml");
    }

    @FXML
    void AbsenseClick(ActionEvent event) {
        setSession(3, "absence/absenceFXML.fxml");
    }

    @FXML
    void chatClick(ActionEvent event) {
        setSession(4, "chat/chatFXML.fxml");
        // reload messages
        
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

        });

    }

    @FXML
    void profileAction(ActionEvent event) {
        if (profileStage == null) {
            profileStage = new Stage();
            LoadFXML load = new LoadFXML();
            load.LoadFXML(FXMLPath.PROFILE);
            Scene scene = new Scene(load.getParent(), 400, 800);
            profileStage.setScene(scene);
            profileStage.show();
            profileStage.setOnCloseRequest(e -> profileStage = null);
        }
    }

    @FXML
    void settingAction(ActionEvent event) {

        System.out.println(Arrays.toString(ref.OnlineStudents.toArray()));
//        menuItem.setStyle("-fx-background-color: #00000000;-fx-text-fill: #ffffff");
        contextValidator.getItems().clear();
        contextValidator.getItems().add(new MenuItem("Logout"));
        contextValidator.show(setting,Side.BOTTOM,0,0);
        contextValidator.setOnAction(e -> {
            logout();
        });
    }

    private void logout() {

        ref.nullize();

        // load main
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.OPENNING);
        OpenningController controller = (OpenningController) load.getController();
        mainRoot.getScene().setRoot(load.getParent());
        CustomStageStyle.undecoratedStageOptions(ref.MAIN_STAGE, controller.getBorder());
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
        Effect.setImageColor(graphic, Color.web(Styles.colorBlack));
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ref.requestList = new ArrayList<>();
        System.out.println(ref.create_innerDB.deleteFrom(Lecture.LECTURE).where(Lecture.LECTURE.LECTURE_ID.eq(5)).execute());

        setMenuIcon();
        colorizeAllPics();

        ObservableList<Node> children = list_box.getChildren();
        JFXButton[] buttonList = new JFXButton[children.size()];
        for (int i = 0; i < children.size(); i++) {
            buttonList[i] = (JFXButton) children.get(i);
        }
        ListAnim = new OpenCloseList(buttonList, ListBordePane);
        ImageSender imageSender = new ImageSender();
        imageSender.newLecture();
        recievingRequests();

        LoadApproveIgnoreAllFXML();
        // hide request Pane 
        numOfRequestPane.setVisible(false);
        // set request number by zero 
        SignUpCounterText.setText("0");

    }

    private void LoadApproveIgnoreAllFXML() {
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.APPROVE_IGNORE_ALL);
        ApproveIgnoreAllFXMLController controller = (ApproveIgnoreAllFXMLController) load.getController();
        controller.setRequestBox(requestBox);
        controller.SignUpCounterInfo(SignUpCounterRect, numOfRequestPane, SignUpCounterText);
        requestBox.getChildren().add(load.getParent());

    }

    private void setMenuIcon() {
        Image userIcon = new Image(Path.USER_REQUEST);
        request = new ImageView(userIcon);
        request.setFitWidth(25);
        request.setFitHeight(25);
        menu_request.setGraphic(request);//setting the user icon to menu user

    }

    private void colorizeAllPics() {
        Effect.setImageColor(request, Color.web(Styles.colorBlack));
        Effect.setImageColor(max, Colors.EXIT_COLOR);
        Effect.setImageColor(min, Colors.EXIT_COLOR);
        Effect.setImageColor(profile, Color.web(Styles.colorBlack));
        Effect.setImageColor(setting, Color.web(Styles.colorBlack));
    }

    public void setSession(int index, String name) {
        if (Sessions[index] == null) {
            try {
                Sessions[index] = FXMLLoader.load(getClass().getResource(Path.sessions_path + name));
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        dynamicScroller.setContent(Sessions[index]);

    }

    private void recievingRequests() {
        ref.server.setFromClientSignUpListener((FromClientSignUp signup) -> {
            System.out.println("User : " + signup.getUsername());
            LoadFXML load = new LoadFXML();
            load.LoadFXML(FXMLPath.REQUEST_SIGNUP);
            RequestFXMLController controller = (RequestFXMLController) load.getController();
            controller.setSignupInfo(signup);
            controller.setRequestBox(requestBox);
            controller.SignUpCounterInfo(SignUpCounterRect, numOfRequestPane, SignUpCounterText);
            requestBox.getChildren().add(load.getParent());
            ref.requestList.add(signup);
        });
    }

    public Parent getParent() {
        return Border;
    }

}
