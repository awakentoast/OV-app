package adsd.demo.ovappavo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
   private ComboBox<String> comboTransport;
   @FXML
   private ComboBox<String> startLocationsCombo;
   @FXML
   private ComboBox<String> destinationLocationsCombo;
   
   @FXML
   private ComboBox<Integer> hoursComboBox;
   @FXML
   private ComboBox<Integer> minutesComboBox;
   
   
   @FXML
   private Text transportTypeText;
   @FXML
   private Text hourText;
   @FXML
   private Text minuteText;
   @FXML
   private Text timer;
   
   
   @FXML
   private ListView<TripDisplayCell> tripDisplay;

   @FXML
   private Canvas mapDisplay;
   GraphicsContext mapDraw;
   
   
   
   private boolean darkMode = false;
   private boolean closeRequest = false;
   private boolean tripListEmpty = true;
   private boolean favoriteTripsAreShown = false;
   
   
   private Data data;
   private final BusData busData = new BusData();
   private final TrainData trainData = new TrainData();
   
   
   private final Time time = new Time();
   
   
   private final TripFile tripHistory = new TripFile("src/main/java/adsd/demo/ovappavo/tripHistory.txt");
   private final TripFile favoriteTrip = new TripFile("src/main/java/adsd/demo/ovappavo/favoriteTrips.txt");
   
   
   private ResourceBundle bundle;
   private ObservableList<String> locationList;
   private List<Trip> shownTrips = new ArrayList<>();
   
   
   //----------------------------------------------------------------------------------------------------------------------------------------------------------------//
   
   
   @FXML
   public void onStartLocationsCombo() {
      System.out.println("OVappController.onStartLocationsCombo");
   }

   @FXML
   public void onDestinationLocationsCombo() {
      System.out.println("OVappController.onDestinationLocationsCombo");
   }
   @FXML
   public void onComboHour() {
      System.out.println("OVappController.Hours");
   }

   @FXML
   public void onComboMinutes() {
      System.out.println("OVappController.Minutes");
   }

   @FXML
   protected void onTransportType() {
      if (Objects.equals(bundle.getString("transportTypeComboBox.StringArray").split(",")[1], comboTransport.getValue())) {
         data = trainData;
         String[] trainLocations = trainData.getLocationNames();
         locationList = FXCollections.observableArrayList(trainLocations);
      }

      if (comboTransport.getValue().equals("Bus")) {
         data = busData;
         String[] busLocations = busData.getLocationNames();
         locationList = FXCollections.observableArrayList(busLocations);
      }

      startLocationsCombo.setItems(locationList);
      startLocationsCombo.getSelectionModel().select(0); // i.e. "Amsterdam"
      
      destinationLocationsCombo.setItems(locationList);
      destinationLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getItems().size() - 1);
      System.out.print("OVappController.onTransportChange:");
      System.out.println(comboTransport.getValue());
   }


   @FXML
   protected void onPlanMyTrip() {
      System.out.println("OVappController.onPlanMyTrip");
      System.out.format("OVType: %s\n", comboTransport.getValue());
      System.out.format("Van:   %s\n", startLocationsCombo.getValue());
      System.out.format("Tot:      %s\n", destinationLocationsCombo.getValue());

      changeTripsOnDisplay(data.getValidRoutes(data.findLocation(startLocationsCombo.getValue()), data.findLocation(destinationLocationsCombo.getValue()), getTime()));

      System.out.println(comboTransport.getValue());
   }
   
   
   private void changeTripsOnDisplay(List<Trip> trips) {
      shownTrips = trips;
      ObservableList<TripDisplayCell> observableTripList;
      
      if (shownTrips.isEmpty()) {
         observableTripList = FXCollections.observableArrayList(new TripDisplayCell(bundle.getString("noTripsAreFound.string")));
         tripListEmpty = true;
      } else {
         List<TripDisplayCell> tripDisplayCellList = new ArrayList<>(shownTrips.size());
         for (Trip trip : shownTrips) {
            boolean[] servicesStart = trip.getStart().getServices();
            boolean[] servicesEnd = trip.getDestination().getServices();
            TripDisplayCell tripDisplayCell = new TripDisplayCell(trip.getStringForDisplay(bundle), servicesStart, servicesEnd);
            tripDisplayCellList.add(tripDisplayCell);
         }
         
         observableTripList = FXCollections.observableArrayList(tripDisplayCellList);
         tripListEmpty = false;
      }
      
      tripDisplay.setItems(observableTripList);
   }

   @FXML
   private void tripSelected() {
      setupCloseEvent();
      if (!tripListEmpty) {
         int tripIndex = tripDisplay.getSelectionModel().getSelectedIndex();
         Trip currentTrip = shownTrips.get(tripIndex);
         if (favoriteTripsAreShown) {
            changeTripsOnDisplay(data.getValidRoutes(currentTrip.getStart(), currentTrip.getDestination(), currentTrip.getDeparture()));
            favoriteTripsAreShown = false;
         } else {
            tripHistory.addTrip(currentTrip);

            drawTrip();
         }
      }
   }


   private void drawTrip() {

   }
   //perform the actions after stage.setOnCloseRequest() if plan my trip has been used or set favorite trip has been used
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
   
   @FXML
   public void onDisplayTripHistory() {
      changeTripsOnDisplay(tripHistory.getAllTrips());
   }
   
   @FXML
   private void onAddFavoriteTripButton() {
      Trip trip = shownTrips.get(tripDisplay.getSelectionModel().getSelectedIndex());
      favoriteTrip.addTrip(trip);
   }
   
   @FXML
   private void onDisplayFavoriteTrips() {
      changeTripsOnDisplay(favoriteTrip.getAllTrips());
      favoriteTripsAreShown = true;
   }
   
   @FXML
   private void onRetourTrip() {
      int beginIndex = startLocationsCombo.getSelectionModel().getSelectedIndex();
      startLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getSelectionModel().getSelectedIndex());
      destinationLocationsCombo.getSelectionModel().select(beginIndex);
      
      onPlanMyTrip();
   }


   // Important method to initialize this Controller object!!!
   public void initialize() {
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),e -> {time.oneSecondPassed(); timer.setText(time.getCurrentTime());} ));
      bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      changeTextOfFields();

      mapDraw = mapDisplay.getGraphicsContext2D();
      
      trainData.setRoute();
      busData.setRoute();
      data = trainData;

      comboTransport.getSelectionModel().select(1);

      System.out.println("init TransportSelectorController ...");

      String[] trainLocations = trainData.getLocationNames();

      locationList = FXCollections.observableArrayList(trainLocations);

      startLocationsCombo.setItems(locationList);
      startLocationsCombo.getSelectionModel().select(0); // i.e. "Amsterdam"

      destinationLocationsCombo.setItems(locationList);
      destinationLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getItems().size() - 1);

      timer.setText(time.getCurrentTime());

      timeline.setCycleCount(Animation.INDEFINITE);
      timeline.play();

      setTime();
      
      //Sets the generation of each field in listView to that of updateItem in TripDisplayCellFactory
      tripDisplay.setCellFactory(param -> new TripDisplayCellFactory());
      
      System.out.println("init TransportSelectorController done");

      setupMap();
   }

   private void setupMap() {
      mapDraw.drawImage(new Image("file:src/main/java/images/OVapp/123456789.png", mapDisplay.getWidth(), mapDisplay.getHeight(), true, true), 0, 0);
      mapDraw.setFill(Color.BLACK);
      mapDraw.setStroke(Color.BLACK);
      mapDraw.strokeRect(0,0, mapDisplay.getWidth(), mapDisplay.getHeight());
      //mapDraw.fillRect(280, 340, 10, 10);
      //mapDraw.fillRect(330, 420, 10, 10);
      drawAllPlaces();
   }

   private void drawAllPlaces() {
      for (Location location : data.getLocations()) {
         double x = location.getLocationX();
         double y = mapDisplay.getHeight() - location.getLocationY();
         System.out.println("Location: " + location.getName() + " x: " + (int) x + " y: " + (int) y);
         mapDraw.fillRect(x, y, 10, 10);
         mapDraw.strokeText(location.getName(), x, y);
      }
   }




   public void setTime() {
      hoursComboBox.setValue(0);
      ObservableList<Integer> hours = FXCollections.observableArrayList();
      for (int i = 0; i <= 23; i++) {
         hours.add(i);
      }
      hoursComboBox.setItems(hours);
      hoursComboBox.getSelectionModel().select(time.getHour());

      // Maak een ObservableList met de minuten (0 tot 59)
      minutesComboBox.setValue(1);
      ObservableList<Integer> minutes = FXCollections.observableArrayList();
      for (int i = 0; i <= 59; i++) {
         minutes.add(i);
      }
      minutesComboBox.setItems(minutes);
      minutesComboBox.getSelectionModel().select(time.getMinute());
   }


   @FXML
   public void onSwitchLanguage() {
      if (Objects.equals(bundle.getLocale().getLanguage(), "en")) {
         bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      }
      else {
         bundle = ResourceBundle.getBundle("languages", new Locale("en"));
      }
      changeTextOfFields();
      if (!shownTrips.isEmpty()) {
         changeTripsOnDisplay(shownTrips);
      }
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
      changeTextOfText(transportTypeText, "transportTypeLabel.text");
      
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

   private LocalTime getTime()
   {
      return LocalTime.of(hoursComboBox.getValue(),minutesComboBox.getValue());
   }
}
