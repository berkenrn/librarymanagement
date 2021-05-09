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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author berke
 */
public class StaffLoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginClicked(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Staffs WHERE Username = ? AND Password = ?");
        statement.setString(1, username.getText());
        statement.setString(2, password.getText());
        ResultSet result = statement.executeQuery();
        boolean userFound = false;
        if (result.next()){
            userFound = true;
        }

        result.close();
        conn.close();

        if (userFound) {
            Parent root = FXMLLoader.load(getClass().getResource("staff.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Staff");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("User not found");
            a.setHeaderText("Error");
            a.show();
        }
    }
}
