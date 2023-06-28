package adsd.demo.ovappavo;

import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;


public class TripDisplayCellFactory extends ListCell<TripDisplayCell> {
    private final Text text;
    private GridPane gridPaneStart = null;
    private GridPane gridPaneEnd = null;
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
            text.setFill(Color.rgb(242,232,42));
        } else {
            text.setFill(Color.BLACK);
        }

        if (empty || tripDisplayCell == null) {
            setGraphic(null);
            //System.out.println("empty non print");
            if (tripDisplayCell != null) {
                text.setText(tripDisplayCell.getDisplayString());
                setGraphic(new Pane(text));
                //System.out.println("empty print");
            } else {
                Pane pane = new Pane();
                setGraphic(pane);
            }
        } else {
            //System.out.println("non empty");
            
            addIconsOfServicesToGridPane(tripDisplayCell.getIconsStart(), gridPaneStart);
            addIconsOfServicesToGridPane(tripDisplayCell.getIconsEnd(), gridPaneEnd);
            
            text.setText(tripDisplayCell.getDisplayString());
            setGraphic(new Pane(text, gridPaneStart, gridPaneEnd));
        }
    }
    public void addIconsOfServicesToGridPane(List<Image> images, GridPane gridpane) {
        int index = -1;
        for (Image image : images) {
            index++;
            ImageView imageView = new ImageView(image);
            gridpane.add(imageView, horizontalPos[index], verticalPos[index]);
        }
    }
}



