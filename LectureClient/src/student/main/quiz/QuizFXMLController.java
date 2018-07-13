/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.main.quiz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import student.constant.Styles;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import network.clientsocket.Models.FromServerQuiz;
import network.serversocket.Models.FromClientQuizAns;
import student.Utility.Notify;
import student.ref;

/**
 *
 * @author Saad Alenany
 */
public class QuizFXMLController implements Initializable {

    @FXML
    private Text timer;

    @FXML
    private VBox scrollcontent;

    @FXML
    private JFXButton send;

    @FXML
    private Text quiz_name;

    private FromServerQuiz quiz;
    private ArrayList<JFXRadioButton> choicesList;
    private int numOfChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ref.ScrollQuizContent = scrollcontent;
        choicesList = new ArrayList<>();
        timer.setVisible(false);
        send.setVisible(false);
        quiz_name.setVisible(false);
    }

    @FXML
    protected void onSend() {
        sendFinishQuiz();
    }

    private VBox generateQuiz(ArrayList<String> choices, String question_data) {
        VBox questionBox = new VBox(20);
        questionBox.setPadding(new Insets(20, 5, 5, 5));
        questionBox.setStyle("-fx-border-color:" + Styles.colorPrimary + " ;");
        questionBox.setAlignment(Pos.CENTER);

        Text questionContent = new Text(question_data);
        questionContent.setStyle("-fx-font: 16 arial;");
        questionBox.getChildren().addAll(questionContent, generateChoices(choices));

        return questionBox;
    }

    private VBox generateChoices(ArrayList<String> choices) {
        VBox choicesBox = new VBox(15);
        choicesBox.setAlignment(Pos.CENTER);
        choicesBox.setPadding(new Insets(0, 0, 0, 20));

        ToggleGroup toggleGroup = new ToggleGroup();
        numOfChoice = choices.size();
        for (int i = 0; i < choices.size(); i++) {
            HBox choiceHBox = new HBox(5);
            JFXRadioButton radioButton = new JFXRadioButton();
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setText(choices.get(i));
            choiceHBox.getChildren().addAll(radioButton);
            choicesBox.getChildren().add(choiceHBox);
            choicesList.add(radioButton);
            if (i + 1 == choices.size()) {
                VBox.setMargin(choiceHBox, new Insets(0, 0, 10, 0));
            }
        }

        return choicesBox;
    }

    public void setQuiz(FromServerQuiz quiz) {
        this.quiz = quiz;
        // visible 
        timer.setVisible(true);
        send.setVisible(true);
        quiz_name.setVisible(true);
        // clear 
        scrollcontent.getChildren().clear();
        // build quiz
        for (int i = 0; i < quiz.getQuizData().size(); i++) {
            VBox generateQuiz = generateQuiz(quiz.getQuizchoices().get(i), quiz.getQuizData().get(i));
            scrollcontent.getChildren().add(generateQuiz);
        }
        this.quiz_name.setText(quiz.getQuiz_name());
        dec_duration(quiz);
    }

    private void dec_duration(FromServerQuiz quiz) {
        new Thread(() -> {
            int min = quiz.getQuiz_duration();
            min--;
            int sec = 59;
            while (true) {
                if (sec >= 10) {
                    timer.setText(min + ":" + sec);
                } else {
                    timer.setText(min + ":0" + sec);
                }
                if (min == 0 && sec == 0) {
                    sendFinishQuiz();
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                if (sec > 0) {
                    sec--;
                } else {
                    min--;
                    sec = 59;
                }

            }
        }).start();
    }

    private void sendFinishQuiz() {

        int result = 0;
        for (int i = 0; i < choicesList.size(); i++) {
            if (choicesList.get(i).isSelected()) {
                int ans_pos = i % numOfChoice;
                int q_pos = i / numOfChoice;
                int right_ans_pos = quiz.getQuizRightAns().get(q_pos);

                if (ans_pos == right_ans_pos) {
                    result++;
                }

            }
        }
        if (quiz.isAllowSeeResult()) {
            Notify.infoMessage("The result is " + result + "/" + quiz.getQuizData().size());
        } else {
            Notify.infoMessage("Your Quiz information has been sent .");
        }
        scrollcontent.getChildren().clear();
        timer.setVisible(false);
        send.setVisible(false);
        quiz_name.setVisible(false);
        Text txt = new Text("No quiz Now");
        txt.setStyle("-fx-font: 55 arial;"
                + "-fx-font-weight: bold ;");
        txt.setFill(Color.web("#154360"));
        scrollcontent.getChildren().add(txt);

        // send result to server 
        FromClientQuizAns ans = new FromClientQuizAns();
        ans.setQuizAns(result);
        ans.setUsername(ref.username);
        ans.setQuiz_id(this.quiz.getQuiz_id());
        ref.client.SendTCP(ans);
    }

}
