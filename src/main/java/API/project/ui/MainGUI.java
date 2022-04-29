package API.project.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import API.project.businessLogic.Manager;
import API.project.controllers.BrowseCompetitionsController;
import API.project.uicontrollers.*;

import java.io.IOException;


public class MainGUI {

  private Window browsecompetitionsWin;

  private Manager businessLogic;
  private Stage stage;
  private Scene scene;

  public Manager getBusinessLogic() {
    return businessLogic;
  }

  public void setBusinessLogic(Manager afi) {
    businessLogic = afi;
  }

  public MainGUI(Manager bl) {
    Platform.startup(() -> {
      try {
        setBusinessLogic(bl);
        init(new Stage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }


  class Window {
    Controller c;
    Parent ui;
  }

  private Window load(String fxmlfile) throws IOException {
    Window window = new Window();
    FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(fxmlfile) );
    loader.setControllerFactory(controllerClass -> {

      if (controllerClass == BrowseCompetitionsController.class) {
        return new BrowseCompetitionsController(businessLogic);
      }  else {
        // default behavior for controllerFactory:
        try {
          return controllerClass.getDeclaredConstructor().newInstance();
        } catch (Exception exc) {
          exc.printStackTrace();
          throw new RuntimeException(exc); // fatal, just bail...
        }

      }

    });
    window.ui = loader.load();
    ((Controller) loader.getController()).setMainApp(this);
    window.c = loader.getController();
    return window;
  }

  public void init(Stage stage) throws IOException {

    this.stage = stage;

    browsecompetitionsWin = load("/BrowseCompetitions.fxml");

    showBrowseCompetitions();

  }

  public void showBrowseCompetitions(){
    setupScene(browsecompetitionsWin.ui, "Rest Client for Competitions", 800, 420);
  }

  private void setupScene(Parent ui, String title, int width, int height) {
    if (scene == null){
      scene = new Scene(ui, width, height);
      scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
      stage.setScene(scene);
    }
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setTitle(title);
    scene.setRoot(ui);
    stage.show();
  }

}

