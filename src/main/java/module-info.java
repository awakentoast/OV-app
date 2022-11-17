module adsd.demo.ovintro {
   requires javafx.controls;
   requires javafx.fxml;


   opens adsd.demo.ovappavo to javafx.fxml;
   exports adsd.demo.ovappavo;
}
