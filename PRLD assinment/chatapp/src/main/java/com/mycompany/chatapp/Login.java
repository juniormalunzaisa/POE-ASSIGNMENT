/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String firstname;
    private String username;
    private String password;
    private String cellNumber;
    private final String lastname;

    // Constructor
    public Login(String firstname, String lastname, String username, String password, String cellNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Check username format
    public boolean checkUsernameFormat() {
        return username.contains("_") && username.length() <= 5;
    }

    // Static password complexity check
    public static boolean isPasswordComplex(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    // Check cellphone number format
    public boolean checkCellphoneNumber(String regcell) {
        String pattern = "^0\\d{9}$";
        return regcell.matches(pattern);
    }

    // Register user with validation
    public String registerUser() {
        if (!checkUsernameFormat()) {
            return "Username not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters long.";
        }

        if (!isPasswordComplex(password)) {
            return "Password is not correctly formatted. It must be at least 8 characters long and include an uppercase letter, a lowercase letter, a digit, and a special character.";
        }

        if (!checkCellphoneNumber(cellNumber)) {
            return "Cellphone number is incorrectly formatted. It should start with '0' and be 10 digits long.";
        }

        return "Registration successful!";
    }
}

