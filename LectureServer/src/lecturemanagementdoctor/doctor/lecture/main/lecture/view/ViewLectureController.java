/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.lecture.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Saad Alenany
 */
public class ViewLectureController implements Initializable {

    // view lecture FXML Components
    @FXML
    TextField FastInputTxt;

    @FXML
    Text locationTxt;

    @FXML
    BorderPane borderRoot;

    //----------------------------------
    private ArrayList<Image> images;
    private int imageIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void process() {
        imageIndex = 0;
        changeImage(images.get(imageIndex));
    }

    @FXML
    protected void onClose() {
        Stage stage = (Stage) borderRoot.getScene().getWindow();
        stage.hide();
        stage.close();
    }

    @FXML
    protected void fullScreenAction() {
        Stage stage = (Stage) borderRoot.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            changeImage(images.get(imageIndex));
        } else {
            stage.setFullScreen(true);
            changeImage(images.get(imageIndex));
        }
    }

    @FXML
    protected void previousAction() {
        if (imageIndex == 0) {
            return;
        }
        imageIndex--;
        changeImage(images.get(imageIndex));
    }

    @FXML
    protected void nextAction() {
        if (imageIndex == images.size() - 1) {
            return;
        }
        imageIndex++;
        changeImage(images.get(imageIndex));
    }

    @FXML
    protected void fastLocAction(KeyEvent event) {
        if (null != event.getCode()) {
            switch (event.getCode()) {
                case ENTER:
                    String loc = FastInputTxt.getText();
                    if (!loc.matches("\\d+") || Integer.parseInt(loc) >= images.size() || Integer.parseInt(loc) < 0) {
                        FastInputTxt.setText("");
                        return;
                    }
                    locationTxt.setText(Integer.parseInt(loc) + " /" + (images.size() - 1));
                    imageIndex = Integer.parseInt(loc) - 1;
                    changeImage(images.get(imageIndex));
                    break;
                case LEFT: {
                    Stage stage = (Stage) borderRoot.getScene().getWindow();
                    if (stage == null) {
                        return;
                    }
                    previousAction();
                    break;
                }
                case RIGHT: {
                    Stage stage = (Stage) borderRoot.getScene().getWindow();
                    if (stage == null) {
                        return;
                    }
                    nextAction();
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void changeImage(Image image) {
        BackgroundImage bgImg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        borderRoot.setBackground(new Background(bgImg));
        locationTxt.setText((imageIndex + 1) + " / " + images.size());
    }

    public BorderPane getParent() {
        return borderRoot;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

}
