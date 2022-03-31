package adsd.demo.ovintro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OVIntro extends Application
{
   @Override
   public void start( Stage stage ) throws IOException
   {
      FXMLLoader fxmlLoader = new FXMLLoader( OVIntro.class.getResource( "OVIntroGUI.fxml" ) );
      Scene      scene      = new Scene( fxmlLoader.load(), 1000, 600 );
      stage.setTitle( "OVIntro!" );
      stage.setScene( scene );
      stage.show();
   }

   public static void main( String[] args )
   {
      Application.launch();
   }
}
