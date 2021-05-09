/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berkenarin.librarymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class RequestedBooksController implements Initializable {
    @FXML
    private TableView<Book> resultList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DBConnection.dbConn();
        ObservableList<Book> bookList = FXCollections.<Book>observableArrayList();

        TableColumn title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));

        TableColumn author = new TableColumn("Author");
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));

        resultList.getColumns().addAll(title, author);

        try {
            String sql = "SELECT * FROM Requests";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                bookList.add(new Book(
                        "",
                        result.getString("Title"),
                        result.getString("Author"),
                        -1
                ));
            }
            resultList.setItems(bookList);
            result.close();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
