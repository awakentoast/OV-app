package adsd.demo.ovappavo;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;


public class DisplayTripCell implements Callback<ListView<DisplayTrip>, ListCell<DisplayTrip>> {
    @Override
    public ListCell<DisplayTrip> call(ListView<DisplayTrip> param) {
        Text text = new Text();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(4);
        gridPane.setVgap(4);
        gridPane.setLayoutX(220);
        gridPane.setLayoutY(20);
        return new ListCell<>() {
            @Override
            public void updateItem(DisplayTrip displayTrip, boolean empty) {
                super.updateItem(displayTrip, empty);

                if (empty || displayTrip == null) {
                    setGraphic(null);
                } else {
                    text.setY(10);
                }

                int[] verticalPos = {0, 0, 1, 1, 0, 0, 1, 1};
                int[] horizontalPos = {0, 1, 0, 1, 6, 7, 6, 7};


                int count = -1;

               for (Image image : displayTrip.getIconsStart()) {
                  ImageView imageView = new ImageView(image);
                  ++count;
                  gridPane.add(imageView, horizontalPos[count], verticalPos[count]);
                }

               for (Image image : displayTrip.getIconsEnd()) {
                  ImageView imageView = new ImageView(image);
                  ++count;
                  gridPane.add(imageView, horizontalPos[count], verticalPos[count]);
               }

                text.setText(displayTrip.getDisplayString());

                setGraphic(new Pane(text, gridPane));
            }
        };
    }
}


//    tripDisplay.setCellFactory(temp -> new ListCell<>() {
//        @Override
//        protected void updateItem(DisplayTrip displayTrip, boolean empty) {
//            super.updateItem(displayTrip, empty);
//
//            if (empty || displayTrip == null) {
//                setGraphic(null);
//            } else {
//                text.setY(10);
////
////               int[] verticalPos = {0, 0, 1, 1, 0, 0, 1, 1};
////               int[] horizontalPos = {0, 1, 0, 1, 6, 7, 6, 7};
////
////
////               int count = -1;
////               for (Image image : displayTrip.getIconsStart()) {
////                  ImageView imageView = new ImageView(image);
////                  ++count;
////                  gridPane.add(imageView, horizontalPos[count], verticalPos[count]);
////               }
////
////               for (Image image : displayTrip.getIconsEnd()) {
////                  ImageView imageView = new ImageView(image);
////                  ++count;
////                  gridPane.add(imageView, horizontalPos[count], verticalPos[count]);
////               }
////
//                text.setText(displayTrip.getDisplayString());
////               setGraphic(new Pane(text, gridPane));
//
//                setGraphic(new Pane(text, imageView));
//            }
//        }
//    });

