/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author RC_Student_lab
 */
 import javax.swing.*;
import java.util.*;

public class MessageSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Message> sentMessages = new ArrayList<>();
    static List<Message> storedMessages = new ArrayList<>();
    static List<Message> disregardedMessages = new ArrayList<>();
    static Map<String, Message> messageHashes = new HashMap<>();
    static Map<String, Message> messageIDs = new HashMap<>();

    public static void main(String[] args) {
        populateTestData();
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show Recently Sent Messages");
            System.out.println("3) Show Full Sent Messages Report");
            System.out.println("4) Display Longest Sent Message");
            System.out.println("5) Search for Message by ID");
            System.out.println("6) Search for Messages by Recipient");
            System.out.println("7) Delete Message by Hash");
            System.out.println("8) Read Stored Messages From File");
            System.out.println("9) Quit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1" -> sendMessages();
                case "2" -> displayMessages(sentMessages, "Recently Sent Messages");
                case "3" -> displayFullReport();
                case "4" -> displayLongestMessage();
                case "5" -> searchById();
                case "6" -> searchByRecipient();
                case "7" -> deleteByHash();
                case "8" -> JOptionPane.showMessageDialog(null, "Feature not implemented.");
                case "9" -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void populateTestData() {
        addMessage("1", "+27834557896", "Did you get the cake?", "Send");
        addMessage("2", "+27838884567", "Where are you? You are late!", "Store");
        addMessage("3", "+27834484567", "Yhooooo, I am at your gate.", "Disregard");
    }

    static void addMessage(String id, String recipient, String content, String flag) {
        Message msg = new Message();
        msg.captureMessageDetails(id, recipient, content, flag);
        messageIDs.put(id, msg);
        messageHashes.put(msg.getHash(), msg);

        switch (flag) {
            case "Send" -> sentMessages.add(msg);
            case "Store" -> storedMessages.add(msg);
            case "Disregard" -> disregardedMessages.add(msg);
        }
    }

    static void sendMessages() {
        int total = Integer.parseInt(JOptionPane.showInputDialog("How many messages to send?"));
        for (int i = 0; i < total; i++) {
            Message msg = new Message();
            msg.captureMessageDetails();
            String flag = msg.getAction();

            switch (flag) {
                case "Send" -> sentMessages.add(msg);
                case "Store" -> storedMessages.add(msg);
                case "Disregard" -> disregardedMessages.add(msg);
                default -> JOptionPane.showMessageDialog(null, "Invalid flag.");
            }
            messageIDs.put(msg.getId(), msg);
            messageHashes.put(msg.getHash(), msg);
            JOptionPane.showMessageDialog(null, msg.displayDetails());
        }
    }

    static void displayMessages(List<Message> messages, String title) {
        StringBuilder sb = new StringBuilder(title + "\n");
        for (Message msg : messages) {
            sb.append(msg.displayDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    static void displayLongestMessage() {
        Message longest = null;
        for (Message msg : sentMessages) {
            if (longest == null || msg.getContent().length() > longest.getContent().length()) {
                longest = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.displayDetails());
    }

    static void searchById() {
        String id = JOptionPane.showInputDialog("Enter Message ID:");
        Message msg = messageIDs.get(id);
        if (msg != null) {
            JOptionPane.showMessageDialog(null, "Message Found:\n" + msg.displayDetails());
        } else {
            JOptionPane.showMessageDialog(null, "Message ID not found.");
        }
    }

    static void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter Recipient Number:");
        StringBuilder sb = new StringBuilder("Messages to " + recipient + ":\n");
        boolean found = false;
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) {
                sb.append(msg.displayDetails()).append("\n\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
        }
    }

    static void deleteByHash() {
        String hash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
        Message msg = messageHashes.remove(hash);
        if (msg != null) {
            sentMessages.remove(msg);
            storedMessages.remove(msg);
            disregardedMessages.remove(msg);
            messageIDs.remove(msg.getId());
            JOptionPane.showMessageDialog(null, "Message deleted:\n" + msg.displayDetails());
        } else {
            JOptionPane.showMessageDialog(null, "Message hash not found.");
        }
    }

    static void displayFullReport() {
        StringBuilder sb = new StringBuilder("Full Report:\n\n");
        for (Message msg : sentMessages) sb.append("Sent: ").append(msg.displayDetails()).append("\n\n");
        for (Message msg : storedMessages) sb.append("Stored: ").append(msg.displayDetails()).append("\n\n");
        for (Message msg : disregardedMessages) sb.append("Disregarded: ").append(msg.displayDetails()).append("\n\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

