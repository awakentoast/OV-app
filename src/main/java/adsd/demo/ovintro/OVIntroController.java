package adsd.demo.ovintro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OVIntroController
{
   @FXML
   private Label welcomeText;

   @FXML
   protected void onHelloButtonClick()
   {
      welcomeText.setText( "Welcome to JavaFX Application!" );
   }
}
