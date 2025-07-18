module com.project.expenseTrackerUI {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jdk.security.auth;
    requires org.jetbrains.annotations;

    opens com.project.expenseTrackerUI.model to javafx.base, com.fasterxml.jackson.databind;

    exports com.project.expenseTrackerUI;
}
