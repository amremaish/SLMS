/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Database.pojos;

/**
 *
 * @author Saad Alenany
 */
public class ChoiceData {

    public int choice_id;
    public String choice_content;
    public int question_id;

    public ChoiceData(int choice_id, String choice_content, int question_id) {
        this.choice_id = choice_id;
        this.choice_content = choice_content;
        this.question_id = question_id;
    }

    public ChoiceData(String choice_content, int question_id) {
        this.choice_content = choice_content;
        this.question_id = question_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(int choice_id) {
        this.choice_id = choice_id;
    }

    public String getChoice_content() {
        return choice_content;
    }

    public void setChoice_content(String choice_content) {
        this.choice_content = choice_content;
    }

    @Override
    public String toString() {
        return "ChoiceData{" + "choice_id=" + choice_id + ", choice_content=" + choice_content + ", question_id=" + question_id + '}';
    }

}
