/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.newRequest.StudentInfoWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amr
 */
public class StudentInfoWindowController implements Initializable {

    @FXML
    private Text departement;

    @FXML
    private Text gender;

    @FXML
    private Text phone;

    @FXML
    private HBox BorderBox;

    @FXML
    private Text email;

    @FXML
    private Text username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void CloseAction(ActionEvent event) {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }

    public void setDepartement(String departement) {
        this.departement.setText(departement);
    }

    public void setGender(String gender) {
        this.gender.setText(gender);
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public HBox getBorderBox() {
        return BorderBox;
    }

}
