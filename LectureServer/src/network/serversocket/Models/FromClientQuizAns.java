/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.serversocket.Models;

import network.shared.models.root;



/**
 *
 * @author Amr
 */
public class FromClientQuizAns extends root {

    private int quizAns;
    private String username;
    private int quiz_id;

    public int getQuizAns() {
        return quizAns;
    }

    public void setQuizAns(int quizAns) {
        this.quizAns = quizAns;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }
}
