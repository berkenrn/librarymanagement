/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berkenarin.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author berke
 */
public class StaffFormController {
    @FXML
    public void onBooksClicked(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("books.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Books");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onStaffsClicked(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("staffs.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Staffs");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onRequestedBooksClicked(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("requestedbooks.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Requested Books");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
