package adsd.demo.ovappavo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
   private Button toggleHighContrast;
   @FXML
   private Button tripHistoryButton;
   @FXML
   private Button addFavoriteTripButton;
   @FXML
   private Button getFavoriteTripButton;
   @FXML
   private Button retourButton;
   @FXML
   private Button doTrip;
  
   

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
   private boolean actionDoneInTripDisplay = false;
   private boolean firstTimeChangeText = true;
   
   
   private Data data;
   private final Data trainData = TrainData.getTrainDataInstance();
   private final Data busData = BusData.getBusDataInstance();
   
   
   private final Time time = new Time();
   
   
   private final TripFile tripHistory = new TripFile("src/main/resources/data/tripHistory.txt");
   private final TripFile favoriteTrip = new TripFile("src/main/resources/data/favoriteTrips.txt");
   
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
      if (Objects.equals(bundle.getString("transportTypeComboBox.StringArray").split(",")[0], comboTransport.getValue())) {
         data = trainData;
      }
      else {
         data = busData;
      }
      
      locationList = FXCollections.observableArrayList(data.getLocationNames());
      
      startLocationsCombo.setItems(locationList);
      startLocationsCombo.getSelectionModel().select(0);
      
      destinationLocationsCombo.setItems(locationList);
      destinationLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getItems().size() - 1);
      
      //draws the map with the locations of the other type
      setupMap();
      
      System.out.print("OVappController.onTransportChange:");
      System.out.println(comboTransport.getValue());
   }


   @FXML
   protected void onPlanMyTrip() {
      System.out.println("OVappController.onPlanMyTrip");
      System.out.format("OVType: %s\n", comboTransport.getValue());
      System.out.format("Van:   %s\n", startLocationsCombo.getValue());
      System.out.format("Tot:      %s\n", destinationLocationsCombo.getValue());

      actionDoneInTripDisplay = true;
      changeTripsOnDisplay(data.getValidRoutes(data.findLocation(startLocationsCombo.getValue()), data.findLocation(destinationLocationsCombo.getValue()), getTime()));
      System.out.println(comboTransport.getValue());
   }

   @FXML
   protected void onDoTrip() {
      setupCloseEvent();
      if (!tripListEmpty) {
         int tripIndex = tripDisplay.getSelectionModel().getSelectedIndex();
         Trip currentTrip = shownTrips.get(tripIndex);
         tripHistory.addTrip(currentTrip);
         drawTrip(currentTrip);
      }
   }
   
   
   private void changeTripsOnDisplay(List<Trip> trips) {
      //sets the values of shownTrips so the corresponding item in the listView will select the correct trip
      System.out.println();
      shownTrips = trips;
      ObservableList<TripDisplayCell> observableTripList;
      //System.out.println(trips + " " +  trips.size());
      if (actionDoneInTripDisplay) {
         //print "not trips found"
         if (shownTrips.isEmpty()) {
            observableTripList = FXCollections.observableArrayList(new TripDisplayCell(bundle.getString("noTripsAreFound.string")));
            tripListEmpty = true;
            tripDisplay.setItems(observableTripList);
         } else {
            //print all the trips
            List<TripDisplayCell> tripDisplayCellList = new ArrayList<>(shownTrips.size());
            for (Trip trip : shownTrips) {
               boolean[] servicesStart = trip.getStart().getServices();
               boolean[] servicesEnd = trip.getDestination().getServices();
               TripDisplayCell tripDisplayCell = new TripDisplayCell(trip.getStringForDisplay(bundle), servicesStart, servicesEnd);
               tripDisplayCellList.add(tripDisplayCell);
            }

            observableTripList = FXCollections.observableArrayList(tripDisplayCellList);
            tripListEmpty = false;
            tripDisplay.setItems(observableTripList);
         }
      }
   }

   private void drawTrip(Trip trip) {
      //reset the map before drawing new route
      setupMap();
      
      mapDraw.setFill(Color.RED);
      mapDraw.setStroke(Color.RED);
      drawLocations(trip.getLocationList(), true);
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
      actionDoneInTripDisplay = true;
      changeTripsOnDisplay(tripHistory.getAllTrips());
   }
   
   @FXML
   private void onAddFavoriteTripButton() {
      setupCloseEvent();
      Trip trip = shownTrips.get(tripDisplay.getSelectionModel().getSelectedIndex());
      favoriteTrip.addTrip(trip);
   }
   
   @FXML
   private void onDisplayFavoriteTrips() {
      actionDoneInTripDisplay = true;
      changeTripsOnDisplay(favoriteTrip.getAllTrips());
   }
   
   @FXML
   private void onRetourTrip() {
      //switches the locations in the 2 boxes
      int beginIndex = startLocationsCombo.getSelectionModel().getSelectedIndex();
      startLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getSelectionModel().getSelectedIndex());
      destinationLocationsCombo.getSelectionModel().select(beginIndex);
      
      onPlanMyTrip();
   }

   // Important method to initialize this Controller object!!!
   public void initialize() {
      //for the timer
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),e -> {time.oneSecondPassed(); timer.setText(time.getCurrentTime());} ));
      
      //loads all the translations
      bundle = ResourceBundle.getBundle("languages", new Locale("nl"));
      changeTextOfFields();
      
      mapDraw = mapDisplay.getGraphicsContext2D();
      
      data = trainData;

      String[] locations = data.getLocationNames();

      locationList = FXCollections.observableArrayList(locations);
      
      comboTransport.getSelectionModel().select(0);

      startLocationsCombo.setItems(locationList);
      startLocationsCombo.getSelectionModel().select(0); // i.e. "Amsterdam"

      destinationLocationsCombo.setItems(locationList);
      destinationLocationsCombo.getSelectionModel().select(destinationLocationsCombo.getItems().size() - 1);

      setTime();
      
      timer.setText(time.getCurrentTime());
      timeline.setCycleCount(Animation.INDEFINITE);
      timeline.play();

      //Sets the generation of each field in listView to that of updateItem in TripDisplayCellFactory
      tripDisplay.setCellFactory(param -> new TripDisplayCellFactory(tripDisplay.getScene()));
      tripDisplay.setItems(FXCollections.observableArrayList());
      addAllToolTips();
      
      setupMap();
      
      System.out.println("init done");
   }

   private void setupMap() {
      //will draw the map, ets called before every new drawn trip so the previous trip is not displayed
      mapDraw.clearRect(0,0, mapDisplay.getWidth(), mapDisplay.getHeight());
      mapDraw.drawImage(new Image("file:src/main/resources/images/map_netherlands.png", mapDisplay.getWidth(), mapDisplay.getHeight(), true, true), 0, 0);
      
      mapDraw.setFill(Color.BLACK);
      mapDraw.setStroke(Color.BLACK);
      
      drawLocations(data.getLocations(), false);
      drawLinesBetweenPlacesWithRoutes();
   }

   private void drawLocations(List<Location> locations, boolean drawLines) {
      double previousX = 0;
      double previousY = 0;
      boolean firstLocation = true;
      
      for (Location location : locations) {
         //System.out.println(location.getName());
         double x = location.getX();
         double y = mapDisplay.getHeight() - location.getY();
         
         //System.out.println("Location: " + location.getName() + " x: " + (int) x + " y: " + (int) y);
         
         mapDraw.fillRect(x, y, 10, 10);
         mapDraw.setLineWidth(1);
         mapDraw.strokeText(location.getName(), x - (location.getName().length() / 2.0) * 5, y - 3, 200);
         
         if (drawLines && !firstLocation) {
            mapDraw.setLineWidth(2);
            mapDraw.strokeLine(x + 5,y + 5, previousX + 5, previousY + 5);
         }
         previousX = x;
         previousY = y;
         
         //this way we don't draw from 0,0 to the first location
         firstLocation = false;
      }
   }
   
   
   private void drawLinesBetweenPlacesWithRoutes() {
      mapDraw.setLineWidth(2);
      Set<String> routeSet = data.getRouteStrings();
      //System.out.println(routeSet);
      
      for (String routeString : routeSet) {
         //System.out.println(routeString);
         String[] routeList = routeString.split("-");
         for (int i = 0; i < routeList.length - 1; i++) {
            Location location1 = data.findLocation(routeList[i]);
            Location location2 = data.findLocation(routeList[i + 1]);
            mapDraw.strokeLine(location1.getX() + 5,mapDisplay.getHeight() - location1.getY() + 5,
                    location2.getX() + 5, mapDisplay.getHeight() - location2.getY() + 5);
         }
      }
   }
   
   private void addAllToolTips() {
      Tooltip tooltip = new Tooltip(bundle.getString("travelHistory.tooltip"));
      tripHistoryButton.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("favoriteTrip.tooltip"));
      addFavoriteTripButton.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("retour.tooltip"));
      retourButton.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("planTrip.tooltip"));
      doTrip.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("changeLanguage.tooltip"));
      switchLanguageButton.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("seeFavoriteTrips.tooltip"));
      getFavoriteTripButton.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("highContrastMode.tooltip"));
      toggleHighContrast.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("begin.tooltip"));
      startLocationsCombo.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("destination.tooltip"));
      destinationLocationsCombo.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("time.tooltip"));
      minutesComboBox.setTooltip(tooltip);
      hoursComboBox.setTooltip(tooltip);
      
      tooltip = new Tooltip(bundle.getString("transportType.tooltip"));
      comboTransport.setTooltip(tooltip);
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
      changeTripsOnDisplay(shownTrips);
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

   //this will preserve the box that was selected upon switching languages
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

      //you can't call getToolTip() in initialize(), which is where this function is also called
      if (!firstTimeChangeText) {
         tripHistoryButton.getTooltip().setText(bundle.getString("travelHistory.tooltip"));
         addFavoriteTripButton.getTooltip().setText(bundle.getString("favoriteTrip.tooltip"));
         retourButton.getTooltip().setText(bundle.getString("retour.tooltip"));
         doTrip.getTooltip().setText(bundle.getString("planTrip.tooltip"));
         switchLanguageButton.getTooltip().setText(bundle.getString("changeLanguage.tooltip"));
         getFavoriteTripButton.getTooltip().setText(bundle.getString("seeFavoriteTrips.tooltip"));
         toggleHighContrast.getTooltip().setText(bundle.getString("highContrastMode.tooltip"));
         startLocationsCombo.getTooltip().setText(bundle.getString("begin.tooltip"));
         destinationLocationsCombo.getTooltip().setText(bundle.getString("destination.tooltip"));
         minutesComboBox.getTooltip().setText(bundle.getString("time.tooltip"));
         hoursComboBox.getTooltip().setText(bundle.getString("time.tooltip"));
         comboTransport.getTooltip().setText(bundle.getString("transportType.tooltip"));
      }
      firstTimeChangeText = false;
   }

   private void changeTextDarkModeButton() {
      //index 0 is the text "switch to dark mode"
      //index 1 is the text "switch to light mode"
      if (darkMode) {
         changeTextOfFieldWithArray(toggleHighContrast, "toggleHighContrast.StringArray", 1);
      }
      else {
         changeTextOfFieldWithArray(toggleHighContrast, "toggleHighContrast.StringArray", 0);
      }
   }
   

   @FXML
   private void toggleHighContrast() {
      Scene scene = mapDisplay.getScene();
      if (scene.getStylesheets().contains("high-contrast.css")) {
         scene.getStylesheets().remove("high-contrast.css");
         scene.getStylesheets().add("normal.css");
      }
      else {
         scene.getStylesheets().add("high-contrast.css");
         scene.getStylesheets().remove("normal.css");
      }
      darkMode = !darkMode;
      changeTextDarkModeButton();
      changeTripsOnDisplay(shownTrips);
   }

   private LocalTime getTime()
   {
      return LocalTime.of(hoursComboBox.getValue(),minutesComboBox.getValue());
   }
}
