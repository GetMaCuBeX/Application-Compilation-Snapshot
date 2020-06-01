package res.fxml.main.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;
import org.mcbx.test.hibernate.test.ConnectionTest;

public class LayoutController implements Initializable {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LayoutController.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

//------------------------------------------------------------------------------ TITLE BAR DRAGGING
    Stage stageWindow;
    double x, y;

    @FXML
    public void dragged(MouseEvent event) {
        stageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageWindow.setX(event.getScreenX() - x);
        stageWindow.setY(event.getScreenY() - y);
    }

    @FXML
    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

//------------------------------------------------------------------------------ WINDOW MAXIMIZE
    @FXML
    public void draggedDoubleClick(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); //------ DISABLE KEY FULLSCREEN EXIT
        if (event.getClickCount() == 2) {
            if (stage.isFullScreen()) {
                stage.setFullScreen(false);
            } else {
                stage.setFullScreen(true);
            }
        }
    }

//------------------------------------------------------------------------------ CLOSE
    @FXML
    public void close(MouseEvent event) {
        try {
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            stage.close();
            System.exit(0);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

//------------------------------------------------------------------------------ MAXIMIZE
    @FXML
    public void maximize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint("");
        // Stage.setFullScreenExitKeyCombination(KeyCombination);
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }

    }

//------------------------------------------------------------------------------ MINIMIZE
    @FXML
    public void minimize(MouseEvent event) {
        Stage stage = null;
        if (event.getClickCount() == 1) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (stage.isIconified()) {
                stage.setIconified(false);
            } else {
                stage.setIconified(true);
            }
        }
    }

    public void displayYourMessage(String message) {
        System.out.println(message);
    }

}
