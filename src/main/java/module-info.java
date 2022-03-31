module adsd.demo.ovintro {
   requires javafx.controls;
   requires javafx.fxml;


   opens adsd.demo.ovintro to javafx.fxml;
   exports adsd.demo.ovintro;
}
