package adsd.demo.ovappavo;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DisplayTripCell extends ListCell<DisplayTrip> {
    public DisplayTripCell() {

    }

    @Override
    protected void updateItem(DisplayTrip displayTrip, boolean empty) {

        final Text text = new Text();
        final ImageView imageView = new ImageView("file:src/main/java/images/OVapp/toiletIcon.jpeg");

        final GridPane gridPane = new GridPane();
        super.updateItem(displayTrip, empty);
        if (empty || displayTrip == null) {
            setGraphic(null);
        } else {
            text.setText(displayTrip.getDisplayString());
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
}
