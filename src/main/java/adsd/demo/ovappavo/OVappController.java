package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;




import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


@SuppressWarnings("deprecation")
public class OVappController
{
   boolean closeRequest = false;
   boolean darkMode = false;

   @FXML private Button getFavoriteTripButton;
   @FXML private Button planMyTripButton;
   @FXML private Button switchLanguageButton;
   @FXML private Button toggleDarkModeButton;
   @FXML private Label  transportTypeLabel;

   @FXML private ComboBox<String> comboTransport;
   @FXML private ComboBox<String> comboA;
   @FXML private ComboBox<String> comboB;
   @FXML private TextArea         textArea;

   ResourceBundle bundle;
   TripHistory tripHistory = new TripHistory();

   TrainData trainData = new TrainData();
   BusData busData = new BusData();



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
      tripHistory.addTrip(text);

      System.out.println(comboTransport.getValue());


      if (comboTransport.getValue().equals("TrainData")||comboTransport.getValue().equals("Trein"))
      {
         System.out.println("treintjee");
         String[] trainlocations = trainData.getTrainLocationsName();

//         ObservableList<String> locationList = FXCollections.observableArrayList(trainlocations);
//
//         comboA.setItems(locationList);
//         comboA.getSelectionModel().select(0); // i.e. "Amsterdam"
//
//         comboB.setItems(locationList);
//         comboB.getSelectionModel().select(comboB.getItems().size() - 1);
         trainData.writeRoutes(comboA.getValue(),comboB.getValue());

      }

      if (comboTransport.getValue().equals("BusData")){
         System.out.println("busjeee");
//         String[] buslocations = busData.getBusLocationName();
//
//         ObservableList<String> locationList = FXCollections.observableArrayList(buslocations);
//
//         comboA.setItems(locationList);
//         comboA.getSelectionModel().select(0); // i.e. "Amsterdam"
//
//         comboB.setItems(locationList);
//         comboB.getSelectionModel().select(comboB.getItems().size() - 1);


      }
      setupCloseEvent();
   }


   @FXML
   protected void onGetFavorite() {
      System.out.println("onFavorite");
      textArea.setText( tripHistory.getFavoriteTrip() );
   }

   //perform the actions after stage.setOnCloseRequest((WindowEvent event) if plan my trip has been used or set favorite trip has been used
   private void setupCloseEvent() {
      if (!closeRequest) {
         Stage stage = (Stage) planMyTripButton.getScene().getWindow();
         stage.setOnCloseRequest((WindowEvent event) -> {
            tripHistory.save();
         });
         closeRequest = true;
      }
   }


   // Important method to initialize this Controller object!!!
   public void initialize() {

      trainData.setRoute();

      bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      changeTextOfFields();
      comboTransport.getSelectionModel().select(1);

      System.out.println("init TransportSelectorController ...");

      String[] buslocations = busData.getBusLocationName();
      String[] trainlocations = trainData.getTrainLocationsName();

      ObservableList<String> locationList = FXCollections.observableArrayList(trainlocations);

      comboA.setItems(locationList);
      comboA.getSelectionModel().select(0); // i.e. "Amsterdam"

      comboB.setItems(locationList);
      comboB.getSelectionModel().select(comboB.getItems().size() - 1);


      System.out.println("init TransportSelectorController done");
   }


   @FXML
   public void switchLanguage() {
      if (Objects.equals(bundle.getLocale().getLanguage(), "en")) {
         bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      }
      else {
         bundle = ResourceBundle.getBundle("languages", new Locale("en"));
      }
      changeTextOfFields();
   }

   private void changeTextOfField(Labeled label, String key) {
      label.setText(bundle.getString(key));
   }

   private void changeTextOfFieldWithArray(Labeled label, String key, int index) {
      label.setText(bundle.getString(key).split(",")[index]);
   }

   private void changeObservableListText(ComboBox<String> comboBox, String key) {
      int index = comboBox.getSelectionModel().getSelectedIndex();
      String[] vehicleListArray = bundle.getString(key).split(",");
      ObservableList<String> vehicleList = FXCollections.observableArrayList(vehicleListArray);
      comboBox.setItems(FXCollections.observableArrayList(vehicleList));
      comboBox.getSelectionModel().select(index);
   }


   private void changeTextOfFields() {
      changeTextOfField(transportTypeLabel, "transportTypeLabel.text");
      changeTextOfField(planMyTripButton, "planMyTripButton.text");
      changeTextOfField(getFavoriteTripButton, "getFavoriteTripButton.text");
      changeTextOfField(switchLanguageButton, "switchLanguageButton.text");

      changeTextDarkModeButton();

      changeObservableListText(comboTransport, "transportTypeComboBox.StringArray");
   }

   private void changeTextDarkModeButton() {
      //index 0 is the text "switch to dark mode"
      //index 1 is the text "switch to light mode"
      if (darkMode) {
         changeTextOfFieldWithArray(toggleDarkModeButton, "toggleDarkModeButton.StringArray", 1);
      }
      else {
         changeTextOfFieldWithArray(toggleDarkModeButton, "toggleDarkModeButton.StringArray", 0);
      }
   }


   @FXML
   private void toggleDarkMode(ActionEvent event) {
      Scene scene = ((Node) event.getSource()).getScene();
      if (scene.getStylesheets().contains("dark-mode.css")) {
         scene.getStylesheets().remove("dark-mode.css");
      }
      else {
         scene.getStylesheets().add("dark-mode.css");
      }
      darkMode = !darkMode;
      changeTextDarkModeButton();
   }
}