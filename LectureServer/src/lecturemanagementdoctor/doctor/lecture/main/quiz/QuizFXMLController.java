/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.quiz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quiz;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quizquestion;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Studentdegrees;
import lecturemanagementdoctor.doctor.Database.pojos.Student;
import lecturemanagementdoctor.doctor.Database.pojos.StudentDegrees;
import lecturemanagementdoctor.doctor.Utility.Notify;
import lecturemanagementdoctor.doctor.constant.Styles;
import lecturemanagementdoctor.doctor.controllers.Validator;
import lecturemanagementdoctor.doctor.ref;
import network.clientsocket.Models.FromServerQuiz;
import network.serversocket.Models.FromClientQuizAns;

/**
 *
 * @author Saad Alenany
 */
public class QuizFXMLController implements Initializable {

    @FXML
    VBox scrollcontent;

    @FXML
    JFXTextField numberofchoices, quiz_name, timePerMinute;

    @FXML
    JFXButton add, remove, send;

    @FXML
    JFXCheckBox allowmarks;

    private int noc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new Validator().ValidateNumber(numberofchoices);
    }

    @FXML
    protected void onAdd() {
        noc = 0;
        try {
            noc = Integer.parseInt(numberofchoices.getText());
        } catch (NumberFormatException numberFormatException) {
        }

        if (noc > 0) {
            numberofchoices.setEditable(false);
            scrollcontent.getChildren().add(generateField());
        } else {
        }

    }

    @FXML
    protected void onRemove() {
        if (scrollcontent.getChildren().size() > 0) {
            scrollcontent.getChildren().remove(scrollcontent.getChildren().size() - 1);
        }
        if (scrollcontent.getChildren().isEmpty()) {
            numberofchoices.setEditable(true);
        }
    }

    @FXML
    protected void onSend() {
        String quizname = quiz_name.getText();
        int tpm = 0;
        try {
            tpm = Integer.parseInt(timePerMinute.getText());
        } catch (NumberFormatException numberFormatException) {
            Notify.errorMessage("Please enter a valid time");
            return;
        }
        if (quizname.equals("")) {
            Notify.errorMessage("Please fill empty fields.");
            return;
        } else if (tpm <= 0) {
            Notify.errorMessage("Please enter a valid time");
        }

        QuizDataOrganizer qdo = new QuizDataOrganizer(quizname, noc, tpm, allowmarks.isSelected(), scrollcontent);
        int returnedQuizID = qdo.getExtractQuizData();
        if (returnedQuizID < 0) {
            Notify.errorMessage("Quiz data not filled.");
        } else {
            System.out.println("quiz id "+returnedQuizID);
            FromServerQuiz modelTransferObject = qdo.getModelTransferObject();
            modelTransferObject.setQuiz_id(returnedQuizID);
            modelTransferObject.setQuiz_name(quizname);
            modelTransferObject.setAllowSeeResult(allowmarks.isSelected());
            modelTransferObject.setQuiz_duration(tpm);
            ref.server.SendToAllTCP(modelTransferObject);
            Notify.infoMessage("Quiz successfully created.");
        }
        StartRecievingQuizResult();
    }

    private VBox generateField() {
        VBox questionBox = new VBox(20);
        questionBox.setPadding(new Insets(20, 5, 5, 5));
        questionBox.setStyle("-fx-border-color:" + Styles.colorPrimary + " ;");
        questionBox.setAlignment(Pos.CENTER);

        JFXTextField questionContent = new JFXTextField();
        questionContent.setPromptText("Type the Question");
        questionContent.setLabelFloat(true);

        questionBox.getChildren().addAll(questionContent, generateChoices());

        return questionBox;
    }

    private VBox generateChoices() {
        VBox choicesBox = new VBox(15);
        choicesBox.setAlignment(Pos.CENTER);
        choicesBox.setPadding(new Insets(0, 0, 0, 20));

        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < noc; i++) {
            HBox choiceHBox = new HBox(5);

            JFXRadioButton radioButton = new JFXRadioButton();
            radioButton.setToggleGroup(toggleGroup);

            JFXTextField choiceField = new JFXTextField();
            choiceField.setPromptText("Click to type answer " + (i + 1));
            choiceField.setLabelFloat(true);

            choiceHBox.getChildren().addAll(radioButton, choiceField);
            choicesBox.getChildren().add(choiceHBox);
        }

        return choicesBox;
    }

    private Separator generateSeparator() {
        Separator separator = new Separator(Orientation.HORIZONTAL);
        return separator;
    }

    private void StartRecievingQuizResult() {
        ref.server.setFromClientQuizAnsListener((FromClientQuizAns qa) -> {
            int student_id = ref.databaseManagerInner.getStudentIDByName(qa.getUsername());

            int totalDegree = ref.create_innerDB.selectFrom(Quizquestion.QUIZQUESTION).where(Quizquestion.QUIZQUESTION.QUIZ_ID.eq(qa.getQuiz_id())).fetch().size();

            System.out.println("username "+qa.getUsername() +
                                "\n result " + qa.getQuizAns() +
                                "\n quiz_id " +qa.getQuiz_id() +
                                "\n student_id "+student_id +
                                "\n totalDegree "+totalDegree);
            StudentDegrees studentDegrees = new StudentDegrees(student_id,qa.getQuiz_id(),qa.getQuizAns(),totalDegree);
            ref.databaseManagerInner.insert(studentDegrees);

            // saad#
            // save quiz result to database
        });
    }

}
