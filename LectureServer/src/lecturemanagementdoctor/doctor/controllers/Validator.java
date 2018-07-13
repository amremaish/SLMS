package lecturemanagementdoctor.doctor.controllers;

import com.jfoenix.controls.JFXTextField;

public class Validator {

    public void ValidateNumber(JFXTextField tf){
        tf.textProperty().addListener(e -> {
            if (!tf.getText().matches("\\d*")) {    //if not match a number character
                tf.setText(tf.getText().replaceAll("[^\\d]", ""));//replace it with nothing
            }
        });
    }

    public void ValidateName(JFXTextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[\\!-\\@\\[-\\`\\{-\\~]")) { //matches number or symbol
                tf.setText(tf.getText().replaceAll("[\\!-\\@\\[-\\`\\{-\\~]", ""));//replace it with nothing
            }
        });
    }

    public void ValidatePhone(JFXTextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[\\!-\\*\\:-\\~ \\, \\/]+")){    //match phone number if doesn't contain 0->9 . - +
                tf.setText(tf.getText().replaceAll("[\\!-\\*\\:-\\~ \\, \\/]+", ""));//replace it with nothing
            }
        });
    }

    public void ValidateEmail(JFXTextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[ \\!-\\* \\, \\/\\:-\\?\\[-\\^\\`\\{-\\~]+")){    //matches if does contain space , symbols except for @ + - _ .
                tf.setText(tf.getText().replaceAll("[ \\!-\\* \\, \\/\\:-\\?\\[-\\^\\`\\{-\\~]+", ""));//replace it with nothing
            }
        });
    }

}
