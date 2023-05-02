package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


@SuppressWarnings("deprecation")
public class OVappController
{
   boolean closeRequest = false;

   public Button getFavoriteTripButton;
   public Button planMyTripButton;
   public Button switchLanguageButton;
   public Label  transportTypeLabel;

   @FXML private ComboBox<String> comboTransport;
   @FXML private ComboBox<String> comboA;
   @FXML private ComboBox<String> comboB;
   @FXML private TextArea         textArea;


   ResourceBundle bundle;
   TripHistory tripHistory = new TripHistory();

   Train train = new Train();

   @FXML
   public void onComboA()
   {
      System.out.println( "OVappController.onComboA" );
   }

   @FXML
   public void onComboB()
   {
      System.out.println( "OVappController.onComboB" );
   }

   @FXML
   protected void onTransport()
   {
      System.out.print( "OVappController.onTransportChange" );
   }

   @FXML
   protected void onPlanMyTrip()
   {
      System.out.println( "OVappController.onPlanMyTrip" );
      System.out.format( "OVType: %s\n", comboTransport.getValue() );
      System.out.format( "Van:   %s\n", comboA.getValue() );
      System.out.format( "Tot:      %s\n", comboB.getValue() );

      String text = String.format("%-8s %-15s\n", "OVType:", comboTransport.getValue()) +
                    String.format("%-8s %-15s\n", "Van:", comboA.getValue()) +
                    String.format("%-8s %-15s\n", "Tot:", comboB.getValue());

      textArea.setText(text);

      //String key = comboTransport.getValue() + comboA.getValue() + comboB.getValue();
      tripHistory.addTrip(text);

      System.out.println(comboTransport.getValue());
      if (comboTransport.getValue().equals("trein"))
      {
         train.setRoute();
         System.out.println("treintjee");

        // train.writeRoutes(comboA.getValue(),comboB.getValue());
         train.writeAllRoutes();
      }

      //triggers tripHistory.save() when the app is closed
      if (!closeRequest) {
         Stage stage = (Stage) planMyTripButton.getScene().getWindow();
         stage.setOnCloseRequest((WindowEvent event) -> tripHistory.save());
         closeRequest = true;
      }
   }


   @FXML
   protected void onGetFavorite() {
      System.out.println("onFavorite");
      textArea.setText( tripHistory.getFavoriteTrip() );
   }


   // Important method to initialize this Controller object!!!
   public void initialize()
   {
      bundle = ResourceBundle.getBundle("languages", new Locale("en"));
      changeTextOfFields();

      System.out.println( "init TransportSelectorController ..." );

      // Initialise the combo box comboA with stopover locations.
      // String[] locations = { "Abcoude", "Amersfoort","Amsterdam","Arnhem","Emmen","Groningen","Haarlem","Maastricht" ,"Nijmegen", "Rotterdam","Utrecht","Vlissingen","Xanten" };

      String[] locations = train.getLocationsName();
      ObservableList<String> locationList = FXCollections.observableArrayList( locations );
      comboA.setItems( locationList );
      comboA.getSelectionModel().select( 0 ); // i.e. "Amsterdam"

      comboB.setItems( locationList );
      comboB.getSelectionModel().select( comboB.getItems().size() - 1 );


      System.out.println( "init TransportSelectorController done" );

   }
   

   @FXML
   public void switchLanguage() {
      if (Objects.equals(bundle.getLocale().getLanguage(), "en")) {
         bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      } else {
         bundle = ResourceBundle.getBundle("languages", new Locale("en"));
      }
      changeTextOfFields();
   }


   private void changeTextOfFields() {
      transportTypeLabel.setText(bundle.getString("transportTypeLabel.text"));
      planMyTripButton.setText(bundle.getString("planMyTripButton.text"));
      getFavoriteTripButton.setText(bundle.getString("getFavoriteTripButton.text"));
      switchLanguageButton.setText(bundle.getString("switchLanguageButton.text"));

      String[] vehicleListArray = bundle.getString("transportTypeComboBox.StringArray").split(",");
      ObservableList<String> vehicleList = FXCollections.observableArrayList(vehicleListArray);
      comboTransport.setItems(FXCollections.observableArrayList(vehicleList));
      comboTransport.getSelectionModel().select(1);
   }
}
