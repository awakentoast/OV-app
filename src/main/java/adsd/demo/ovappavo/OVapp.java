package adsd.demo.ovappavo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

public class OVapp extends Application
{
   @Override
   public void start( Stage stage ) throws IOException
   {
      FXMLLoader languageBundleLoader = new FXMLLoader(getClass().getResource("OVappGUI.fxml"));
      languageBundleLoader.setResources(ResourceBundle.getBundle("languages"));
      Parent root = languageBundleLoader.load();
      stage.setTitle( "Mijn Prachtige Applicatie" );
      stage.setScene( new Scene(root) );
      stage.show();
      
   }

   public static void main(String[] args )
   {
      Application.launch();
   }
}
