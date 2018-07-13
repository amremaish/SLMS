/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.absence;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import lecturemanagementdoctor.doctor.Database.pojos.Absence;
import lecturemanagementdoctor.doctor.Utility.Notify;
import lecturemanagementdoctor.doctor.ref;

import static lecturemanagementdoctor.doctor.ref.absenceList;

/**
 *
 * @author Saad Alenany
 */
public class AbsenceFXMLController implements Initializable {

    @FXML
    public JFXTextField searchField;
    @FXML
    public JFXRadioButton exclude;
    @FXML
    public JFXRadioButton save;
    @FXML
    private ListView<JFXCheckBox> absenceList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ref.absenceList = this.absenceList;
        for (int i = 0; i < ref.StudentAbsenceList.size(); i++) {
            JFXCheckBox temp = new JFXCheckBox(ref.StudentAbsenceList.get(i));
            temp.setStyle("-fx-font-size: 18;");
            temp.setSelected(true);
            ref.absenceList.getItems().add(temp);
        }

    }

    public void takeUnSavedAction(ActionEvent actionEvent) {
        if (ref.CURRENT_LECTURE == null){
            Notify.errorMessage("You'll have to start the lecture before you take absence");
            return;
        }
        for (int i=0 ; i<this.absenceList.getItems().size() ; i++) {
            if (this.absenceList.getItems().get(i).isSelected()){
                String student = this.absenceList.getItems().get(i).getText();
                int studid = ref.databaseManagerInner.getStudentIDByName(student);
                if (!studentWasTakenInAbsenceBefore(studid)){
                    ref.databaseManagerInner.insert(new Absence(studid, ref.CURRENT_LECTURE.getLecture_id()));
                }
            }else{
                String student = this.absenceList.getItems().get(i).getText();
                int studid = ref.databaseManagerInner.getStudentIDByName(student);
                ref.create_innerDB.deleteFrom(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE)
                        .where(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE.STUDENT_ID.eq(studid))
                        .and(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE.LECTURE_ID.eq(ref.CURRENT_LECTURE.getLecture_id()))
                        .execute();
            }
        }

    }

    public void doAction(ActionEvent actionEvent) {

    }

    private boolean studentWasTakenInAbsenceBefore(int id) {
        if (ref.CURRENT_LECTURE == null){
            return false;
        }
        return ref.create_innerDB.selectFrom(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE)
                .where(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE.STUDENT_ID.eq(id))
                .and(lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence.ABSENCE.LECTURE_ID.eq(ref.CURRENT_LECTURE.lecture_id)).fetch().size() >= 1;
    }

}
