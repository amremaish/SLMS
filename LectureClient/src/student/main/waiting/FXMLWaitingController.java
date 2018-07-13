package student.main.waiting;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import student.Utility.Notify;

public class FXMLWaitingController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    DisplayClient ds;

    @FXML
    void CheckRunningAction(ActionEvent event) {
        Thread th = new Thread(() -> {
            try {
                if (ds == null) {
                    ds = new DisplayClient();
                } else {
                    ds.getFrame().setVisible(true);
                }
            } catch (IOException ex) {
                Notify.errorMessage("Doctor doesn't start lecture yet");
            }
        });
        th.start();
    }

}
