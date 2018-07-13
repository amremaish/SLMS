/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.stat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

/**
 *
 * @author Saad Alenany
 */
public class StatFXMLController implements Initializable{

    public TabPane tabs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        tabs.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);

        tabs.getTabs().addAll(new AbsenceTable(),new QuizRate(),new AbsenceRate());
//        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

}
