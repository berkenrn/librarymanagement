module com.berkenarin.librarymanagement {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens com.berkenarin.librarymanagement to javafx.fxml;
    exports com.berkenarin.librarymanagement;
}
