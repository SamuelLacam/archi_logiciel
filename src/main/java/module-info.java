module org.example.tp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tp2 to javafx.fxml;
    exports org.example.tp2;
    exports org.example.tp2.messages;
    opens org.example.tp2.messages to javafx.fxml;
    exports org.example.tp2.server;
    opens org.example.tp2.server to javafx.fxml;
    exports org.example.tp2.model;
    opens org.example.tp2.model to javafx.fxml;
    exports org.example.tp2.controller;
    opens org.example.tp2.controller to javafx.fxml;
}