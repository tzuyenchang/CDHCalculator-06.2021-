module com.tzuyen.CDHCalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens com.tzuyen.CDHCalculator to javafx.fxml;
    opens com.tzuyen.CDHCalculator.playerDataModel to javafx.base;
    exports com.tzuyen.CDHCalculator;
}