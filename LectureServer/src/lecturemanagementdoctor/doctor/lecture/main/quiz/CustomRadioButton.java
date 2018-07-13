/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.quiz;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lecturemanagementdoctor.doctor.constant.Styles;

/**
 *
 * @author Saad Alenany
 */
public class CustomRadioButton extends HBox {

    private JFXRadioButton radioButton = new JFXRadioButton();
    private JFXTextField ChangedField = new JFXTextField();

    public CustomRadioButton(String data, ToggleGroup group) {
        this.setPrefWidth(Double.MAX_VALUE);
        this.setAlignment(Pos.CENTER_LEFT);
        CreateRadioButton(data, group);
        ClickAction();
    }

    private void CreateRadioButton(String data, ToggleGroup group) {
        radioButton.setText(data);
        radioButton.setFont(Font.font(14));
//        radioButton.setStyle("-fx-text-fill : #fff");
        radioButton.setToggleGroup(group);
//        radioButton.setSelectedColor(Color.web("fff"));
        this.getChildren().add(radioButton);
    }

    private JFXTextField CreateTextField(String txt) {
        ChangedField.setPromptText(txt);
        ChangedField.setLabelFloat(true);
        ChangedField.setFont(Font.font(13));
        VBox.setMargin(ChangedField, new Insets(0, 0, 0, 50));
        ChangedField.setStyle(
//                "-fx-background-color :  #5BA6D7 ; " +
//                "-fx-border-color : #000; " +
                "-fx-prompt-text-fill: "+Styles.colorText+";" +
                "-fx-text-fill: "+Styles.colorText+";");
        return ChangedField;
    }

    private void ClickAction() {
        radioButton.setOnMouseClicked(e -> {
            String preTxt = radioButton.getText();
            this.getChildren().clear();
            this.getChildren().add(CreateTextField(preTxt));
        });
    }

    public void transformTextFieldToRadio() {
        String preTxt = ChangedField.getText();
        if (!preTxt.equals("")) {
            radioButton.setText(preTxt);
        }
        this.getChildren().clear();
        this.getChildren().add(radioButton);
    }

    public String getText() {
        return radioButton.getText();
    }

    public JFXRadioButton getRadioButton() {
        return radioButton;
    }

    public void setText(String txt) {
        radioButton.setText(txt);
    }
}
