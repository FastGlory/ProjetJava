module com.example.projetdiego {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.json;

    opens com.example.projetdiego to javafx.fxml;
    exports com.example.projetdiego;
}