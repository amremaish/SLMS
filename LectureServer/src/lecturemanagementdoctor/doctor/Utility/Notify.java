/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Amr
 */
public class Notify {

    public static void infoMessage(String msg) {
        new Alert(AlertType.INFORMATION, msg).showAndWait();
    }

    public static void NoneMessage(String msg){
        new Alert(AlertType.NONE, msg).showAndWait();
    }

    public static void errorMessage(String msg) {
        new Alert(AlertType.ERROR, msg).showAndWait();
    }

    public static void WarningMessage(String msg) {
        new Alert(AlertType.WARNING, msg).showAndWait();
    }

    public static void ConfirmationMessage(String msg) {
        new Alert(AlertType.CONFIRMATION, msg).showAndWait();
    }

}
