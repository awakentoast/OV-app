package adsd.demo.ovappavo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.*;


@SuppressWarnings("deprecation")
public class OVappController {

   
   @FXML
   private Button planMyTripButton;
   @FXML
   private Button switchLanguageButton;
   @FXML
   private Button toggleDarkModeButton;
   @FXML
   private Button tripHistoryButton;
   @FXML
   private Button addFavoriteTripButton;
   @FXML
   private Button getFavoriteTripButton;
   @FXML
   private Button retourButton;
   

   @FXML
   private Label transportTypeLabel;
   

   @FXML
   private ComboBox<String> comboTransport;
   @FXML
   private ComboBox<String> comboA;
   @FXML
   private ComboBox<String> comboB;
   
   @FXML
   private ComboBox<Integer> hoursComboBox;
   @FXML
   private ComboBox<Integer> minutesComboBox;
   

   @FXML
   private Text hourText;
   @FXML
   private Text minuteText;
   @FXML
   private Text timer;
   
   
   @FXML
   private ListView<String> tripDisplay;
   
   
   private boolean darkMode = false;
   private boolean closeRequest = false;
   private boolean viewingHistory = false;
   
   
   
   Data data;
   BusData busData = new BusData();
   TrainData trainData = new TrainData();
   
   Time time = new Time();
   private final TripHistory tripHistory = new TripHistory();
   private final FavoriteTrip favoriteTrip = new FavoriteTrip();
   
   private ResourceBundle bundle;
   ObservableList<String> locationList;
   List<Trip> possibleTrips;
   
   
   
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
         String[] trainLocations = trainData.getLocationNames();
         locationList = FXCollections.observableArrayList(trainLocations);
      }

      if (comboTransport.getValue().equals("Bus")) {
         data = busData;
         String[] busLocations = busData.getLocationNames();
         locationList = FXCollections.observableArrayList(busLocations);
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
      viewingHistory = false;
      // tripDisplay.setItems(locationList);
      System.out.println("OVappController.onPlanMyTrip");
      System.out.format("OVType: %s\n", comboTransport.getValue());
      System.out.format("Van:   %s\n", comboA.getValue());
      System.out.format("Tot:      %s\n", comboB.getValue());
      
      
      //data.writeRoutes(comboA.getValue(), comboB.getValue(),getTime(),textArea);
      possibleTrips = data.writeRoutes(comboA.getValue(), comboB.getValue(), getTime());
      List<String> tripStrings = new ArrayList<>(possibleTrips.size());
      
      for (Trip trip : possibleTrips) {
         tripStrings.add(trip.getStringForDisplay());
      }
      
      ObservableList<String> observableRouteList = FXCollections.observableArrayList(tripStrings);
      
      //tripDisplay = new ListView<>(observableRouteList);
      tripDisplay.setItems(observableRouteList);
      
      System.out.println(comboTransport.getValue());

      //tripHistory.addTrip();
      setupCloseEvent();
   }
   
   @FXML
   private void tripSelected() {

      int tripIndex = tripDisplay.getSelectionModel().getSelectedIndex();
      Trip currentTrip = possibleTrips.get(tripIndex);
      tripHistory.addTrip(currentTrip);

   }


   //perform the actions after stage.setOnCloseRequest((WindowEvent event) if plan my trip has been used or set favorite trip has been used
   private void setupCloseEvent() {
      if (!closeRequest) {
         Stage stage = (Stage) planMyTripButton.getScene().getWindow();
         stage.setOnCloseRequest((WindowEvent event) -> {
            tripHistory.save();
            favoriteTrip.save();
         });
         closeRequest = true;
      }
   }
   
   public void displayTripHistory() {
      viewingHistory = true;
      List<Trip> travelHistory = tripHistory.getAllTrips();
      possibleTrips = travelHistory;
      List<String> travelHistoryStrings = new ArrayList<>(travelHistory.size());
      
      for (Trip trip : travelHistory) {
         travelHistoryStrings.add(trip.getStringForDisplay());
      }
      
      ObservableList<String> observableTripHistoryList = FXCollections.observableArrayList(travelHistoryStrings);
      
      //tripDisplay = new ListView<>(observableRouteList);
      tripDisplay.setItems(observableTripHistoryList);
   }
   
   @FXML
   private void onFavoriteTripButton() {
      Trip trip = possibleTrips.get(tripDisplay.getSelectionModel().getSelectedIndex());
      favoriteTrip.addFavorite(trip);
   }
   
   @FXML
   private void displayFavoriteTrips() {
      List<Trip> travelHistory = favoriteTrip.getAllTrips();
      possibleTrips = travelHistory;
      List<String> travelHistoryStrings = new ArrayList<>(travelHistory.size());
      
      for (Trip trip : travelHistory) {
         travelHistoryStrings.add(trip.getStringForDisplay());
      }
      
      ObservableList<String> observableTripHistoryList = FXCollections.observableArrayList(travelHistoryStrings);
      
      //tripDisplay = new ListView<>(observableRouteList);
      tripDisplay.setItems(observableTripHistoryList);
   }
   
   @FXML
   private void retourTrip() {
      int beginIndex = comboA.getSelectionModel().getSelectedIndex();
      comboA.getSelectionModel().select(comboB.getSelectionModel().getSelectedIndex());
      comboB.getSelectionModel().select(beginIndex);
      
      onPlanMyTrip();
   }


   // Important method to initialize this Controller object!!!
   public void initialize() {
      Timeline timeline = new Timeline(
              new KeyFrame(Duration.seconds(1),
                      e -> {
                         time.oneSecondPassed();
                         timer.setText(time.getCurrentTime());
                      }));
      
      
      bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      changeTextOfFields();
      trainData.setRoute();
      busData.setRoute();
      data = trainData;
      //data.locations.putAll(trainData.locationMap);
      //data.locations.putAll(busData.locationMap);

      comboTransport.getSelectionModel().select(1);

      System.out.println("init TransportSelectorController ...");

      String[] trainLocations = trainData.getLocationNames();
      

      locationList = FXCollections.observableArrayList(trainLocations);

      comboA.setItems(locationList);
      comboA.getSelectionModel().select(0); // i.e. "Amsterdam"

      comboB.setItems(locationList);
      comboB.getSelectionModel().select(comboB.getItems().size() - 1);

      timer.setText(time.getCurrentTime());

      timeline.setCycleCount(Animation.INDEFINITE);
      timeline.play();

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


   public void setTime() {
      hoursComboBox.setValue(0);
      ObservableList<Integer> hours = FXCollections.observableArrayList();
      for (int i = 1; i <= 24; i++) {
         hours.add(i);
      }
      hoursComboBox.setItems(hours);

      // Maak een ObservableList met de minuten (0 tot 59)
      minutesComboBox.setValue(1);
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

   private void changeTextOfText(Text text, String key) {
      text.setText(bundle.getString(key));
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
      //Buttons
      changeTextOfField(transportTypeLabel, "transportTypeLabel.text");
      changeTextOfField(planMyTripButton, "planMyTripButton.text");
      changeTextOfField(getFavoriteTripButton, "getFavoriteTripButton.text");
      changeTextOfField(switchLanguageButton, "switchLanguageButton.text");
      changeTextOfField(tripHistoryButton, "tripHistoryButton.text");
      changeTextOfField(getFavoriteTripButton, "getFavoriteTripButton.text");
      changeTextOfField(addFavoriteTripButton, "addFavoriteTripButton.text");
      changeTextOfField(retourButton, "retourTripButton.text");
      
      //Texts
      changeTextOfText(hourText, "hourButtonText.text");
      changeTextOfText(minuteText, "minuteButtonText.text");
      
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
      return LocalTime.of(hoursComboBox.getValue(),minutesComboBox.getValue());
   }
   
   
}