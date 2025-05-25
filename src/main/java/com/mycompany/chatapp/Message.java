/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import org.json.JSONObject;

/**
 * Represents a text message with recipient, content, and tracking info.
 */
public class Message {

    // Static variable to keep track of the total number of messages sent across all instances
    private static int messageSentCount = 0;

    // Instance variables for each message
    private int numMessages;               // Unique number based on messageSentCount
    private String messageID;              // Random 9-digit message ID
    private String recipient;              // Phone number of recipient
    private String messageContent;         // Actual content of the message
    private String messageHash;            // Unique hash combining content and ID for tracking

    /**
     * Constructor to initialize a Message object.
     * Validates recipient and content, then generates ID and hash.
     */
    public Message(String recipient, String messageContent) {
        // Validate recipient phone number format
        if (!isValidRecipient(recipient)) {
            throw new IllegalArgumentException("Invalid recipient number. Must start with +27 and be followed by 9 digits.");
        }

        // Validate message content length
        if (messageContent.length() > 250) {
            throw new IllegalArgumentException("Message exceeds 250 characters.");
        }

        this.recipient = recipient.trim();                  // Trim any spaces from the phone number
        this.messageContent = messageContent;               // Store the message content
        this.messageID = generateMessageID();               // Generate a unique 9-digit ID
        this.numMessages = ++messageSentCount;              // Increment and assign message count
        this.messageHash = createMessageHash();             // Create a hash for the message
    }

    /**
     * Returns the message content.
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Generates a random 9-digit ID for the message.
     */
    private String generateMessageID() {
        Random rand = new Random();
        int id = 100000000 + rand.nextInt(900000000); // Ensures number is between 100,000,000 and 999,999,999
        return String.valueOf(id);
    }

    /**
     * Validates if the message ID is exactly 9 digits long.
     */
    public boolean isValidMessageID() {
        return messageID != null && messageID.length() == 9;
    }

    /**
     * Validates the recipient number format.
     * Must start with +27 and be followed by 9 digits (South African mobile number format).
     */
    public boolean isValidRecipient(String number) {
        return number != null && number.matches("\\+27\\d{9}");
    }

    /**
     * Creates a unique message hash.
     * Format: First two digits of ID : message count : FIRSTWORDLASTWORD (from content).
     */
    private String createMessageHash() {
        String[] words = messageContent.trim().split(" ");      // Split content into words
        String firstWord = words[0].toUpperCase();              // First word in uppercase
        String lastWord = words[words.length - 1].toUpperCase();// Last word in uppercase
        String idPrefix = messageID.substring(0, 2);            // First 2 digits of ID
        return idPrefix + ":" + numMessages + ":" + firstWord + lastWord;
    }

    /**
     * Returns a message string based on the selected option.
     * 1 - success, 2 - delete, 3 - stored, default - invalid.
     */
    public String getMessageOption(int option) {
        return switch (option) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option entered.";
        };
    }

    /**
     * Converts the message details to a JSON object for storage or transfer.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("messageHash", messageHash);
        json.put("recipient", recipient);
        json.put("message", messageContent);
        return json;
    }

    /**
     * Prints all the relevant message details in a formatted string.
     */
    public String printMessageDetails() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageContent;
    }

    /**
     * Returns the total number of messages sent across all instances.
     */
    public static int getTotalMessagesSent() {
        return messageSentCount;
    }
}
