package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.time.LocalTime;

@SuppressWarnings("deprecation")
public class OVappController
{

   public Button getFavoriteTripButton;
   public Button planMyTripButton;
   public Button switchLanguageButton;
   public Label transportVehicleText;

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
   }


   @FXML
   protected void onGetFavorite() {
      System.out.println("onGetFavorite");
      textArea.setText( tripHistory.getFavoriteTrip() );
   }

   @FXML
   protected void onSetFavorite() {
      System.out.println("onSetFavorite");
      tripHistory.addFavorite(new Trip(LocalTime.of(10,15),"Utrecht", "Abcoude", ));
   }




   // Important method to initialize this Controller object!!!
   public void initialize()
   {
      bundle = ResourceBundle.getBundle("languages", new Locale("en"));
      changeTextOfFields();


      System.out.println( "init TransportSelectorController ..." );

      // Initialise the combo box comboTransport with transportation types ...
      String[] ovtypes = { "vliegtuig", "trein", "bus", "tram", "taxi" };

      ObservableList<String> vehicleList = FXCollections.observableArrayList( ovtypes );
      comboTransport.setItems( vehicleList );
      comboTransport.getSelectionModel().select( 2 ); // i.e. "train"


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

   private void changeTextOfFields() {
     // transportVehicleText.setText(bundle.getString("transportVehicleText.text"));
      planMyTripButton.setText(bundle.getString("planMyTripButton.text"));
      getFavoriteTripButton.setText(bundle.getString("getFavoriteTripButton.text"));
      switchLanguageButton.setText(bundle.getString("switchLanguageButton.text"));
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
}
