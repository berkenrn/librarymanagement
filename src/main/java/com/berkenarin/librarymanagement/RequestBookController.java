/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berkenarin.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author berke
 */
public class RequestBookController {
   @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    public void onRequestClick(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Requests (Title, Author) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, title.getText());
        statement.setString(2, author.getText());
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Book successfully requested");
        a.setHeaderText("Requested");
        a.show();

        conn.close();
    } 
}
