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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class BooksController implements Initializable {
   @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField publishedAt;
    @FXML
    private TableView<Book> resultList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupBooks();
    }

    private void setupBooks() {
        Connection conn = DBConnection.dbConn();
        ObservableList<Book> bookList = FXCollections.<Book>observableArrayList();

        TableColumn isbn = new TableColumn("Isbn");
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));

        TableColumn title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));

        TableColumn author = new TableColumn("Author");
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));

        TableColumn publishedAt = new TableColumn("PublishedAt");
        publishedAt.setCellValueFactory(new PropertyValueFactory<Book, String>("PublishedAt"));

        resultList.getColumns().addAll(isbn, title, author, publishedAt);

        try {
            String sql = "SELECT * FROM Books";
            PreparedStatement statement = conn.prepareStatement(sql);
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
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onAddBookClicked(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Books (Isbn, Title, Author, PublishedAt) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, isbn.getText());
        statement.setString(2, title.getText());
        statement.setString(3, author.getText());
        statement.setInt(4, Integer.parseInt(publishedAt.getText()));
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Book successfully added");
        a.setHeaderText("Added");
        a.show();

        conn.close();

        setupBooks();
    } 
}
