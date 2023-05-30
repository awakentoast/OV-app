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

import java.time.LocalTime;
import java.util.*;


@SuppressWarnings("deprecation")
public class OVappController {

   @FXML
   private Button getFavoriteTripButton;
   @FXML
   private Button planMyTripButton;
   @FXML
   private Button switchLanguageButton;
   @FXML
   private Button toggleDarkModeButton;

   @FXML
   private Label transportTypeLabel;

   @FXML
   private ComboBox<String> comboTransport;
   @FXML
   private ComboBox<String> comboA;
   @FXML
   private ComboBox<String> comboB;
   @FXML
   private TextArea textArea;
   @FXML
   private ComboBox<Integer> hoursComboBox;

   @FXML
   private ComboBox<Integer> minutesComboBox;


   private boolean darkMode = false;
   private boolean closeRequest = false;

   private ResourceBundle bundle;

   private final TripHistory tripHistory = new TripHistory();
   TrainData trainData = new TrainData();
   BusData busData = new BusData();
   Data data;
   ObservableList<String> locationList;


   @FXML
   public void onComboA() {
      System.out.println("OVappController.onComboA");
   }

   @FXML
   public void onComboB() {
      System.out.println("OVappController.onComboB");
   }
   @FXML
   public void onComboHour(){
      System.out.println("ovappcontroller.Hours");}

      @FXML
      public void onComboMinutes(){
         System.out.println("ovappcontroller.Minutes");
   }

   @FXML
   protected void onTransport() {
      if (comboTransport.getValue().equals("Train") || comboTransport.getValue().equals("Trein")) {
         data = trainData;
         String[] trainlocations = trainData.getTrainLocationsName();
         locationList = FXCollections.observableArrayList(trainlocations);
      }

      if (comboTransport.getValue().equals("Bus")) {
         data = busData;
         String[] buslocations = busData.getBusLocationName();
         locationList = FXCollections.observableArrayList(buslocations);
      }

      comboA.setItems(locationList);
      comboA.getSelectionModel().select(0); // i.e. "Amsterdam"

      comboB.setItems(locationList);
      comboB.getSelectionModel().select(comboB.getItems().size() - 1);
      System.out.print("OVappController.onTransportChange:");
      System.out.println(comboTransport.getValue());


   }


   @FXML
   protected void onPlanMyTrip() {


      // tripDisplay.setItems(locationList);
      System.out.println("OVappController.onPlanMyTrip");
      System.out.format("OVType: %s\n", comboTransport.getValue());
      System.out.format("Van:   %s\n", comboA.getValue());
      System.out.format("Tot:      %s\n", comboB.getValue());

      String text = String.format("%-8s %-15s\n", "OVType:", comboTransport.getValue()) +
              String.format("%-8s %-15s\n", "Van:", comboA.getValue()) +
              String.format("%-8s %-15s\n", "Tot:", comboB.getValue());


    //  textArea.setText(text);

      tripHistory.addTrip(text);
      data.writeRoutes(comboA.getValue(), comboB.getValue(),getTime(),textArea);

      System.out.println(comboTransport.getValue());

   }


   @FXML
   protected void onGetFavorite() {
      System.out.println("onGetFavorite");
      textArea.setText(tripHistory.getFavoriteTrip());
   }

   @FXML
   protected void onAddFavorite() {
      System.out.println("onSetFavorite");
   //   tripHistory.addFavorite(new Trip(LocalTime.of(10, 15), new Location("Utrecht"), new Location("Abcoude")));
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
      // busData.setRoute();
      data = trainData;
      data.locations.putAll(trainData.trainLocationMap);
      data.locations.putAll(busData.busLocationMap);

      bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      changeTextOfFields();
      comboTransport.getSelectionModel().select(1);

      System.out.println("init TransportSelectorController ...");

      String[] trainlocations = trainData.getTrainLocationsName();

      ObservableList<String> locationList = FXCollections.observableArrayList(trainlocations);

      comboA.setItems(locationList);
      comboA.getSelectionModel().select(0); // i.e. "Amsterdam"

      comboB.setItems(locationList);
      comboB.getSelectionModel().select(comboB.getItems().size() - 1);
      setTime();

//      // Maak een ObservableList met de uren (0 tot 24)
//      ObservableList<Integer> hours = FXCollections.observableArrayList();
//      for (int i = 1; i <= 24; i++) {
//         hours.add(i);
//      }
//      hoursComboBox.setItems(hours);
//
//      // Maak een ObservableList met de minuten (0 tot 59)
//      ObservableList<Integer> minutes = FXCollections.observableArrayList();
//      for (int i = 0; i <= 59; i++) {
//         minutes.add(i);
//      }
//      minutesComboBox.setItems(minutes);


      System.out.println("init TransportSelectorController done");
}

public void setTime()
{
   ObservableList<Integer> hours = FXCollections.observableArrayList();
   for (int i = 1; i <= 24; i++) {
      hours.add(i);
   }
   hoursComboBox.setItems(hours);

   // Maak een ObservableList met de minuten (0 tot 59)
   ObservableList<Integer> minutes = FXCollections.observableArrayList();
   for (int i = 0; i <= 59; i++) {
      minutes.add(i);
   }
   minutesComboBox.setItems(minutes);
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

   @FXML
   private void onTimeSelected() {
      // Haal de geselecteerde uren en minuten op uit de ComboBoxen
      int selectedHour = hoursComboBox.getSelectionModel().getSelectedItem();
      int selectedMinute = minutesComboBox.getSelectionModel().getSelectedItem();

      // Doe iets met de geselecteerde tijd (uren en minuten)
      System.out.println("Geselecteerde tijd: " + selectedHour + ":" + selectedMinute);
   }

   private LocalTime getTime()
   {

     var time = LocalTime.of(hoursComboBox.getValue(),minutesComboBox.getValue());
      return time;
   }
}