package student.Utility.Stage.undecoratedStage;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Amr
 */
public class StageStyle {

    public static void undecoratedStageOptions(Stage stage, Parent root) {
      
        DragStage.DragStage(root, stage);
        ResizeHelper.addResizeListener(stage);
        
    }
      
    
}
