/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

/**
 *
 * @author RC_Student_lab
 */ package com.mycompany.chatapp;

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
        }

        // === PART 2: Login ===
        JOptionPane.showMessageDialog(null, "=== User Login ===");
        String loginUsername = JOptionPane.showInputDialog("Enter your Username to login:");
        String loginPassword = JOptionPane.showInputDialog("Enter your Password to login:");
        String loginStatus = user.returnLoginStatus(loginUsername, loginPassword);
        JOptionPane.showMessageDialog(null, loginStatus);

        if (!loginStatus.equals("Login successful.")) {
            return;
        }

        // === PART 3: Messaging ===
        try {
            String recipient = JOptionPane.showInputDialog("Enter recipient number (e.g., +27831234567):");
            String messageContent = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            Message message = new Message(recipient, messageContent);

            // Show message details
            String messageDetails = message.printMessages();
            JOptionPane.showMessageDialog(null, "Message Created:\n\n" + messageDetails);

            // Ask user to select an option
            String messageOptions = JOptionPane.showInputDialog(
                "Select an option:\n1 - Send message\n2 - Delete message\n3 - Store message\nOther - Invalid Option"
            );
            int option = Integer.parseInt(messageOptions);

            // Display option result
            String result = message.MessageOptions(option);
            JOptionPane.showMessageDialog(null, result);

            // If storing, display JSON
            if (option == 3) {
                JSONObject stored = message.storeMessage();
                JOptionPane.showMessageDialog(null, "Message stored: \n" + stored.toString(4));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }
}
