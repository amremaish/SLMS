/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.openning;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lecturemanagementdoctor.doctor.Database.InnerDataBaseManager;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.Doctor;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.records.DoctorRecord;
import lecturemanagementdoctor.doctor.Utility.Effect;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.Utility.Pair;
import lecturemanagementdoctor.doctor.constant.Colors;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.lecture.main.chat.MessageBlock.MessageBlockFXMLController;
import lecturemanagementdoctor.doctor.lecture.main.chat.StudentRow.StudentRowController;
import lecturemanagementdoctor.doctor.lecture.openning.login.LoginFXMLController;
import lecturemanagementdoctor.doctor.ref;
import network.serversocket.Listener.FromClientLoginListener;
import network.serversocket.Models.FromClientLogin;
import network.serversocket.ServerSocket;
import network.shared.models.Message;
import org.jooq.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.paint.Color;
//import slider.SliderFX;

/**
 *
 * @author Saad Alenany
 */
public class OpenningController implements Initializable {

    @FXML
    private HBox boxSlider;

    @FXML
    public HBox doctorsBox;
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
        ref.MAIN_STAGE.setResizable(false);
        ref.OnlineStudents = new ArrayList<>();
        ref.Messages = new HashMap<>();
        ref.StudentAbsenceList = new ArrayList<>();
        SetImage();

        doctorsBox.setAlignment(Pos.CENTER);
        doctorsBox.setPadding(new Insets(25, 10, 25, 10));
        viewDoctorsDataBases();

        // load login 
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.LOGIN);
        LoginFXMLController controller = (LoginFXMLController) load.getController();
        controller.setMainPane(LoginStackPane);
        VBox b = (VBox) load.getParent();
        LoginStackPane.getChildren().add(b);

        // networking
        startNetWorking();
        StartRecievingLogin();
        StartRecievingMessages();
    }

    private void SetImage() {
        // slider
        slideImageView.setFitHeight(505);
        slideImageView.setFitWidth(677);
        slideImageView.setImage(new Image("/res/lectureServer.png"));

    }

    private void viewDoctorsDataBases() {
        Result<DoctorRecord> doctorRecords = ref.databaseManagerOuter.getAll("Doctor").into(Doctor.DOCTOR);
        for (DoctorRecord r : doctorRecords) {
            JFXButton dbButton = new JFXButton();
            dbButton.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            dbButton.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            dbButton.setPadding(new Insets(10, 10, 10, 10));
            dbButton.setContentDisplay(ContentDisplay.TOP);
            dbButton.setText(r.getDoctorCourse());
            dbButton.setTextFill(Color.web("#85C1E9"));
            // dbButton.setStyle("-fx-border-color:#359add ;-fx-background-color: #359add");
            dbButton.setCursor(Cursor.HAND);
            ImageView imageView = new ImageView("/res/database.png");
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            dbButton.setGraphic(imageView);

            doctorsBox.getChildren().add(dbButton);
            dbButton.setOnMouseClicked(e -> {
                for (Node node : doctorsBox.getChildren()) {
                    node.setEffect(null);
                }
                JFXButton src = (JFXButton) e.getSource();
                src.setEffect(new Bloom(0.5));
                ref.CURRENT_COURSE = src.getText();
            });
        }
    }

    private void startNetWorking() {
        try {
            ref.server = new ServerSocket();
            ref.server.Start();
        } catch (Exception ignored) {
        }
    }

    public BorderPane getBorder() {
        return border;
    }

    private void StartRecievingLogin() {
        ref.server.setFromClientLoginListener(new FromClientLoginListener() {
            @Override
            public int FromClientLogin(FromClientLogin login) {
                // check from database
                // saad#
                // return 1 for accept , 2 for reject
                int returned = 2;

                //means there is no database for the doctor & there for there are no students
                if (ref.databaseManagerInner == null) {
                    return returned;
                }
                System.out.println("checking user " + login.getUsername() + " , " + login.getPassword());
                if (ref.databaseManagerInner.checkStudentUsernameAndPassword(login.getUsername(), login.getPassword()) != 0) {
                    System.out.println(login.getUsername() + " exists");
                    returned = 1;

                    boolean found = false;
                    for (int i = 0; i < ref.OnlineStudents.size(); i++) {
                        if (ref.OnlineStudents.get(i).equals(login.getUsername())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        if (ref.student_contacts != null) {
                            Platform.runLater(() -> {
                                addRowContact(login.getUsername());
                            });
                        }
                        ref.OnlineStudents.add(login.getUsername());
                    }

                    // absence
                    if (ref.absenceList == null) {
                        ref.StudentAbsenceList.add(login.getUsername());
                    } else if (!studentWasTakenInAbsenceBefore(login.getUsername())) {
                        JFXCheckBox temp = new JFXCheckBox(login.getUsername());
                        temp.setStyle("-fx-font-size: 18;");
                        temp.setSelected(true);
                        Platform.runLater(() -> {
                            ref.absenceList.getItems().add(temp);
                        });
                    }
                }

                return returned;
            }
        });
    }

    private boolean studentWasTakenInAbsenceBefore(String username) {
        if (ref.CURRENT_LECTURE == null) {
            return false;
        }
        int id = ref.databaseManagerInner.getStudentIDByName(username);
        return ref.create_innerDB.selectFrom(Absence.ABSENCE).where(Absence.ABSENCE.STUDENT_ID.eq(id))
                .and(Absence.ABSENCE.LECTURE_ID.eq(ref.CURRENT_LECTURE.getLecture_id())).fetch().size() >= 1;
    }

    private void StartRecievingMessages() {
        ref.server.setFromClientMessageListener((Message Message) -> {
            if (ref.MSG_V_BOX != null && ref.usernameTxtMsg.getText().equals(Message.getStudent_username())) {
                // add message 
                LoadFXML load = new LoadFXML();
                load.LoadFXML(FXMLPath.MESSAGE_BLOCK);
                MessageBlockFXMLController controller = (MessageBlockFXMLController) load.getController();
                controller.setStudentMsg(Message.getMessage());
                addMessageToBox(load.getParent());
            }

            if (ref.Messages.get(Message.getStudent_username()) == null) {
                ref.Messages.put(Message.getStudent_username(), new ArrayList<>());
            }

            ArrayList<Pair<String, Integer>> get = ref.Messages.get(Message.getStudent_username());
            get.add(new Pair<>(Message.getMessage(), ref.STUDENT));
        });

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

    private void addRowContact(String username) {
        Platform.runLater(() -> {
            LoadFXML load = new LoadFXML();
            load.LoadFXML(FXMLPath.STUDENT_ROW);
            StudentRowController controller = (StudentRowController) load.getController();
            controller.setUsernameText(username);
            ref.student_contacts.getChildren().add(load.getParent());
        });
    }

    private boolean loadData(DoctorRecord doctorRecord) {
        if (doctorRecord != null) {
            ref.LOGGED_DOCTOR = new lecturemanagementdoctor.doctor.Database.pojos.Doctor(doctorRecord.getDoctorId(),
                    doctorRecord.getDoctorName(),
                    doctorRecord.getDoctorPassword(),
                    doctorRecord.getDoctorEmail(),
                    doctorRecord.getDoctorGender(),
                    doctorRecord.getDoctorCourse(),
                    doctorRecord.getDoctorPhone(),
                    doctorRecord.getDoctorDepartment(),
                    doctorRecord.getDoctorDatabase());

            ref.databaseManagerInner = new InnerDataBaseManager(ref.LOGGED_DOCTOR.doctor_database);

            return true;
        }
        return false;
    }
}
