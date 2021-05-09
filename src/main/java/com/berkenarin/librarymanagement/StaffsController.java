/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berkenarin.librarymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 *
 * @author berke
 */
public class StaffsController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TableView<Staff> resultList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupStaffs();
    }

    private void setupStaffs() {
        Connection conn = DBConnection.dbConn();
        ObservableList<Staff> staffList = FXCollections.<Staff>observableArrayList();

        TableColumn firstName = new TableColumn("FirstName");
        firstName.setCellValueFactory(new PropertyValueFactory<Staff, String>("FirstName"));

        TableColumn lastName = new TableColumn("LastName");
        lastName.setCellValueFactory(new PropertyValueFactory<Staff, String>("LastName"));

        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<Staff, String>("Username"));

        resultList.getColumns().addAll(firstName, lastName, username);

        try {
            String sql = "SELECT * FROM Staffs";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                staffList.add(new Staff(
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Username")
                ));
            }
            resultList.setItems(staffList);
            result.close();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onAddStaffClicked(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Staffs (FirstName, LastName, Username, Password) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, firstName.getText());
        statement.setString(2, lastName.getText());
        statement.setString(3, username.getText());
        statement.setString(4, password.getText());
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Staff successfully added");
        a.setHeaderText("Added");
        a.show();

        conn.close();

        setupStaffs();
    }
}
