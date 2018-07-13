/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor;

import com.jfoenix.controls.JFXCheckBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lecturemanagementdoctor.doctor.Database.InnerDataBaseManager;
import lecturemanagementdoctor.doctor.Database.OuterDataBaseManager;
import lecturemanagementdoctor.doctor.Database.pojos.Doctor;
import lecturemanagementdoctor.doctor.Database.pojos.Lecture;
import lecturemanagementdoctor.doctor.Utility.Pair;
import network.clientsocket.Models.FromServerQuiz;
import network.serversocket.Models.FromClientSignUp;
import network.serversocket.ServerSocket;

import org.jooq.DSLContext;

/**
 *
 * @author Amr
 */
public class ref {

    public static Stage MAIN_STAGE;
    public static boolean CLOSED_LIST = false;
    public static VBox MSG_V_BOX;
    public static DSLContext create;
    public static Doctor LOGGED_DOCTOR;
    public static ServerSocket server;
    public static VBox student_contacts;
    public static InnerDataBaseManager databaseManagerInner;
    public static OuterDataBaseManager databaseManagerOuter;
    public static DSLContext create_innerDB;
    public static DSLContext create_outerDB;
    public static ArrayList<String> OnlineStudents;
    public static ListView<JFXCheckBox> absenceList;
    public static ArrayList<String> StudentAbsenceList;
    public static ArrayList<FromClientSignUp> requestList ; 
    public static Text usernameTxtMsg;
    //-----------------------------------------
    // map with student username and all messages with doc or student 
    // second list for messages (message , (doc or student))
    public static HashMap<String, ArrayList<Pair<String, Integer>>> Messages;
    public static final int DOCTOR = 1, STUDENT = 2;
    //-----------------------------------------
    // quiz now 
    // if quiz running send to new looged student
    public static boolean quiz_now;
    public static FromServerQuiz quizRunning;
    public static String CURRENT_COURSE;

    /*
    *
    * Lecture can only happen once in a day for 1 doctor with 1 course
    * */
    //---------Current Lecture-----------------
    public static Lecture CURRENT_LECTURE;

    public static void nullize(){
        //reset Static Variables
        ref.create = null;
        ref.create_innerDB = null;
        ref.create_outerDB = null;
        ref.CURRENT_LECTURE = null;
        ref.databaseManagerInner = null;
        ref.absenceList = null;
        ref.StudentAbsenceList = null;
        ref.LOGGED_DOCTOR = null;
        ref.MSG_V_BOX = null;
        ref.server = null;
        ref.student_contacts = null;
        ref.Messages = null;
        ref.OnlineStudents = null;
        ref.usernameTxtMsg = null;
        ref.CLOSED_LIST = false;
        ref.quiz_now = false;
        ref.quizRunning = null;
        ref.databaseManagerOuter = new OuterDataBaseManager();
    }
}
