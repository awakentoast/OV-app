package adsd.demo.ovappavo;

import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class TripDisplayCellFactory extends ListCell<TripDisplayCell> {
    private final Text text;
    private final GridPane gridPaneStart;
    private final GridPane gridPaneEnd;
    private final Scene scene;
    
    //positions for icon in GridPane
    int[] verticalPos = {0, 0, 1, 1};
    int[] horizontalPos = {0, 1, 0, 1};
    
    
    public TripDisplayCellFactory(Scene scene) {
        this.scene = scene;
        gridPaneStart = new GridPane();
        gridPaneEnd = new GridPane();
        text = new Text();
        text.setY(10);
        
        setupGridPane(gridPaneStart);
        setupGridPane(gridPaneEnd);
        
        gridPaneStart.setLayoutX(240);
        gridPaneStart.setLayoutY(20);
        
        gridPaneEnd.setLayoutX(400);
        gridPaneEnd.setLayoutY(20);
    }
    
    private void setupGridPane(GridPane gridPane) {
        gridPane.setHgap(4);
        gridPane.setVgap(4);
    }
    
    @Override
    protected void updateItem(TripDisplayCell tripDisplayCell, boolean empty) {
        
        //System.out.println(tripDisplayCell);
        super.updateItem(tripDisplayCell, empty);
        gridPaneStart.getChildren().clear();
        gridPaneEnd.getChildren().clear();
        
        if (scene.getStylesheets().contains("high-contrast.css")) {
            getStyleClass().add("custom-cell");
            text.setFill(Color.rgb(242,232,42));
        } else {
            System.out.println("false");
            getStyleClass().remove("custom-cell");
            text.setFill(Color.BLACK);
        }
        
        //the check for string is so the cell clears when there are no trips, but no trips are found is still printed to the listView
        if ((empty || tripDisplayCell == null) || tripDisplayCell.getDisplayString().length() < 30) {
            setGraphic(null);
            if (tripDisplayCell != null) {
                text.setText(tripDisplayCell.getDisplayString());
                setGraphic(new Pane(text));
            }
        } else {
            int index = -1;
            for (Image image : tripDisplayCell.getIconsStart()) {
                index++;
                ImageView imageView = new ImageView(image);
                gridPaneStart.add(imageView, horizontalPos[index], verticalPos[index]);
            }

            index = -1;
            for (Image image : tripDisplayCell.getIconsEnd()) {
                index++;
                ImageView imageView = new ImageView(image);
                gridPaneEnd.add(imageView, horizontalPos[index], verticalPos[index]);
            }
            
            text.setText(tripDisplayCell.getDisplayString());
            setGraphic(new Pane(text, gridPaneStart, gridPaneEnd));
        }
    }
}



