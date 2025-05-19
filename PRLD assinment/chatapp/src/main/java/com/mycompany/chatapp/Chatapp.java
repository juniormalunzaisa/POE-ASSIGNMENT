/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import javax.swing.JOptionPane;
import org.json.JSONObject;

public class MainUser {

    public static void main(String[] args) {
        Login user = new Login();

        // === PART 1: Registration ===
        String firstName = JOptionPane.showInputDialog("Enter your First Name:");
        String lastName = JOptionPane.showInputDialog("Enter your Last Name:");
        String username = JOptionPane.showInputDialog("Enter a Username (max 5 characters, must contain '_'):");
        String password = JOptionPane.showInputDialog("Enter a Password (min 8 chars, with uppercase, number, and special character):");
        String cellNumber = JOptionPane.showInputDialog("Enter Cell Number (e.g., +27831234567):");

        user.setFirstname(firstName);
        user.setLastName(lastName);

        String registerMessage = user.registerUser(username, password, cellNumber);
        JOptionPane.showMessageDialog(null, registerMessage);

        if (!registerMessage.equals("User registered successfully.")) {
            JOptionPane.showMessageDialog(null, "Registration failed. Exiting...");
            return;

