package com.berkenarin.librarymanagement;

public class Staff {
    private String FirstName;
    private String LastName;
    private String Username;

    public Staff(String firstName, String lastName, String username) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
