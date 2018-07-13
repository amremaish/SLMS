/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.clientsocket.Models;

import java.util.ArrayList;
import network.shared.models.root;

/**
 *
 * @author Amr
 */
public class FromServerQuiz extends root {

    private int quiz_id;
    private String quiz_name;
    private ArrayList<String> QuizData;
    private ArrayList<Integer> QuizRightAns;
    private int quiz_duration ;
    private ArrayList<ArrayList<String>> Quizchoices;
    private boolean AllowSeeResult;

    public FromServerQuiz() {
        QuizData = new ArrayList<>();
        Quizchoices = new ArrayList<>();
        QuizRightAns = new ArrayList<>();
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public ArrayList<String> getQuizData() {
        return QuizData;
    }

    public void setQuizData(ArrayList<String> QuizData) {
        this.QuizData = QuizData;
    }

    public ArrayList<ArrayList<String>> getQuizchoices() {
        return Quizchoices;
    }

    public void setQuizchoices(ArrayList<ArrayList<String>> Quizchoices) {
        this.Quizchoices = Quizchoices;
    }

    public boolean isAllowSeeResult() {
        return AllowSeeResult;
    }

    public void setAllowSeeResult(boolean AllowSeeResult) {
        this.AllowSeeResult = AllowSeeResult;
    }

    public ArrayList<Integer> getQuizRightAns() {
        return QuizRightAns;
    }

    public void setQuizRightAns(ArrayList<Integer> QuizRightAns) {
        this.QuizRightAns = QuizRightAns;
    }

    public int getQuiz_duration() {
        return quiz_duration;
    }

    public void setQuiz_duration(int quiz_duration) {
        this.quiz_duration = quiz_duration;
    }

}
