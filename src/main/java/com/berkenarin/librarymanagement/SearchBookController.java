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
import java.util.ResourceBundle;
/**
 *
 * @author berke
 */
public class SearchBookController implements Initializable {
    @FXML
    private TableView<Book> resultList;
    @FXML
    private RadioButton isbn;
    @FXML
    private RadioButton title;
    @FXML
    private RadioButton author;
    @FXML
    private TextField keyword;

    @FXML
    public void onSearchClick(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();
        ObservableList<Book> bookList = FXCollections.<Book>observableArrayList();

        String sql;
        if (isbn.isSelected()) {
            sql = "SELECT * FROM Books WHERE Isbn LIKE ?";
        } else if (title.isSelected()) {
            sql = "SELECT * FROM Books WHERE Title LIKE ?";
        } else if (author.isSelected()) {
            sql = "SELECT * FROM Books WHERE Author LIKE ?";
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Please select type");
            a.setHeaderText("Error");
            a.show();
            return;
        }

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + keyword.getText() + "%");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            bookList.add(new Book(
                    result.getString("Isbn"),
                    result.getString("Title"),
                    result.getString("Author"),
                    result.getInt("PublishedAt")
            ));
        }
        resultList.setItems(bookList);
        result.close();
        conn.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn isbn = new TableColumn("Isbn");
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));

        TableColumn title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));

        TableColumn author = new TableColumn("Author");
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));

        TableColumn publishedAt = new TableColumn("PublishedAt");
        publishedAt.setCellValueFactory(new PropertyValueFactory<Book, String>("PublishedAt"));

        resultList.getColumns().addAll(isbn, title, author, publishedAt);
    }
}
