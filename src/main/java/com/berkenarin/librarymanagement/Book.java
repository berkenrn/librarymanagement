package com.berkenarin.librarymanagement;

public class Book {
    private String ISBN;
    private String Title;
    private String Author;
    private int PublishedAt;

    public Book(String isbn, String title, String author, int publishedAt) {
        ISBN = isbn;
        Title = title;
        Author = author;
        PublishedAt = publishedAt;
    }

    public int getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(int publishedAt) {
        PublishedAt = publishedAt;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
