module org.apache.maven.cantinappdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;


    opens org.apache.maven.cantinappdesktop to javafx.fxml;
    exports org.apache.maven.cantinappdesktop;

    opens org.apache.maven.cantinappdesktop.data.service to
            com.google.gson,
            javafx.base;
    opens org.apache.maven.cantinappdesktop.view to
            javafx.fxml;
}