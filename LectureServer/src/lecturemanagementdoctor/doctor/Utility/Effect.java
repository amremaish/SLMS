/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author Amr
 */
public class Effect {

    public static void setImageColor(ImageView img, Color color) {
        InnerShadow ds = new InnerShadow();
        ds.setWidth(img.getFitWidth());
        ds.setHeight(img.getFitHeight());
        ds.setChoke(1.0);
        ds.setColor(color);
        img.setEffect(ds);
    }

}
