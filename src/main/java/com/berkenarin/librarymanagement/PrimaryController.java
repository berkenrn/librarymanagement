package com.berkenarin.librarymanagement;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void onSearchBookClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("searchbook.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Search Book");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void onRequestBookClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("requestbook.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Request Book");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void onStaffLoginClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stafflogin.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Staff Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
