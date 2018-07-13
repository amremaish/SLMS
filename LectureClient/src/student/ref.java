/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.clientsocket.ClientSocket;
import student.Utility.Pair;
import student.pojos.Student;

/**
 *
 * @author Amr
 */
public class ref {

    public static String username;
    public static Stage MAIN_STAGE;
    public static boolean CLOSED_LIST = false;
    public static VBox MSG_V_BOX;
    public static ClientSocket client;
    public static ArrayList<Pair<String, Integer>> Messages;
    public static final int DOCTOR = 1, STUDENT = 2;
    public static VBox ScrollQuizContent;

    public static void nullize(){
        //reset Static Variables
        ref.username = null;
        ref.client = null;
        ref.MSG_V_BOX = null;
        ref.Messages = null;
        ref.CLOSED_LIST = false;
        ref.ScrollQuizContent = null;
    }
}
