/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.broadcastingtutorial;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to the server.");

            // Send messages from the main thread
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {
                String message;
                while (true) {
                    System.out.println("Please enter your message! ");
                    message = scanner.nextLine();
                    writer.println(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

