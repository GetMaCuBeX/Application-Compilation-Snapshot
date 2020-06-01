package org.mcbx.main;

import res.fxml.main.controllers.LayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cfg.logger.LogSetProperties; 

public class App extends Application {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(App.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    @Override
    public void start(Stage primaryStage) {
        try {
//------------------------------------------------------------------------------            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/main/Layout.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
            primaryStage.setOnCloseRequest((event) -> {
                Platform.exit();
                System.exit(0);
            });

            LOG.info("This is info : " + "parameter/exception object");
            LOG.warn("This is warn : " + "parameter/exception object");
            LOG.error("This is error : " + "parameter/exception object");
//------------------------------------------------------------------------------ GET THE CONTROLLER            
            Object o = loader.getController();
            if (o instanceof LayoutController) {
                LayoutController controller = (LayoutController) o;
                controller.displayYourMessage("Im calling my controller...");
            }
        } catch (IOException e) {
            LOG.error("SQLEception", e);
            Platform.exit();
            System.exit(0);
        }
    }

    public static void main(String[] args) { 
        launch(args);
    }
}
