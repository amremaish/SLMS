/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.quiz;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quizquestion;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.QuizRecord;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.QuizquestionRecord;
import lecturemanagementdoctor.doctor.Database.pojos.ChoiceData;
import lecturemanagementdoctor.doctor.Database.pojos.Quiz;
import lecturemanagementdoctor.doctor.Database.pojos.QuizQuestion;
import lecturemanagementdoctor.doctor.ref;
import network.clientsocket.Models.FromServerQuiz;

/**
 *
 * @author Saad Alenany
 */
public class QuizDataOrganizer {

    private ArrayList<QuizQuestion> quizQuestions;
    private ArrayList<ChoiceData> arrayOfChoices;
    private String quizname;
    private int noc;
    private int tpm;
    private boolean selected;
    private VBox scrollcontent;

    public QuizDataOrganizer(String quizname, int noc, int tpm, boolean selected, VBox scrollcontent) {
        this.quizname = quizname;
        this.noc = noc;
        this.tpm = tpm;
        this.selected = selected;
        this.scrollcontent = scrollcontent;

    }

    public int getExtractQuizData() {
        quizQuestions = new ArrayList<>(); 
        arrayOfChoices = new ArrayList<>();
        Quiz quiz = new Quiz(quizname);
        QuizRecord quizRecord = ref.databaseManagerInner.insert(quiz).into(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quiz.QUIZ);
        quiz.setQuiz_id(quizRecord.getQuizId());

        for (Node child : scrollcontent.getChildren()) {

            QuizQuestion quizQuestion = new QuizQuestion(noc, "", -1, quizRecord.getQuizId());
            QuizquestionRecord quizquestionRecord = ref.databaseManagerInner.insert(quizQuestion).into(Quizquestion.QUIZQUESTION);
            if (child instanceof VBox) {
                quizquestionRecord.setQuestionData(((JFXTextField) ((VBox) child).getChildren().get(0)).getText());
                VBox choicesBox = (VBox) ((VBox) child).getChildren().get(1);
                for (int i = 0; i < choicesBox.getChildren().size(); i++) {
                    Node ch = choicesBox.getChildren().get(i);
                    if (ch instanceof HBox) {
                        JFXRadioButton radioButton = (JFXRadioButton) ((HBox) ch).getChildren().get(0);
                        if (radioButton.isSelected()) {
                            quizquestionRecord.setRightAnswer(i);
                        }
                        JFXTextField choiceAns = (JFXTextField) ((HBox) ch).getChildren().get(1);
                        String choiceData = choiceAns.getText();
                        if (!choiceData.equals("")) {
                            ChoiceData data = new ChoiceData(choiceData, quizquestionRecord.getQuestionId());
                            ref.databaseManagerInner.insert(data);
                            arrayOfChoices.add(data);
                        } else {
                            return -1;
                        }
                    }
                }
            }
            ref.create_innerDB.executeUpdate(quizquestionRecord);
            System.out.println(quizquestionRecord);
            quizQuestion = new QuizQuestion(quizquestionRecord.getQuestionId()
                                            ,quizquestionRecord.getNumberofchoices()
                                            ,quizquestionRecord.getQuestionData()
                                            ,quizquestionRecord.getRightAnswer()
                                            ,quizquestionRecord.getQuestionId());
            quizQuestions.add(quizQuestion);
        }
        System.out.println(quizQuestions);
        System.out.println(arrayOfChoices);
        return quiz.getQuiz_id();
    }

    public FromServerQuiz getModelTransferObject() {
        FromServerQuiz quiz = new FromServerQuiz();
        for (int i = 0; i < quizQuestions.size(); i++) {
            QuizQuestion qq = quizQuestions.get(i);
            int numberOfChoices = qq.getNumberofchoices();
            quiz.getQuizchoices().add(new ArrayList<>());
            for (int j = i * numberOfChoices; j < numberOfChoices + i * numberOfChoices; j++) {
                quiz.getQuizchoices().get(i).add(arrayOfChoices.get(j).getChoice_content());
            }
            quiz.getQuizData().add(qq.getQuestion_data());
            quiz.getQuizRightAns().add(qq.getRight_answer());

        }
        return quiz;
    }

}
