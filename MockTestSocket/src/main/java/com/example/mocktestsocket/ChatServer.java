/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mocktestsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


// This is the main class named ChatServer.
public class ChatServer {
    // A static final Logger object is created, which will be used for logging information and errors.
    private static final Logger LOGGER = Logger.getLogger(ChatServer.class.getName());

    // The main method which is the entry point of any Java application.
    public static void main(String[] args) {
        // A try-catch block to handle exceptions.
        try {
            // Create a new ServerSocket object that listens on port 5000.
            ServerSocket serverSocket = new ServerSocket(5000);
            // Log an informational message indicating that the server has started and is waiting for clients.
            LOGGER.log(Level.INFO, "Server started. Waiting for clients...");

            // An infinite loop to continuously accept new client connections.
            while (true) {
                // Accept a new client connection and create a Socket object for communication with the client.
                Socket clientSocket = serverSocket.accept();
                // Log an informational message indicating that a client has connected.
                LOGGER.log(Level.INFO, "Client connected: " + clientSocket.getInetAddress());

                // Create a new Thread object for each client. The ClientHandler class implements the Runnable interface, so it can be passed to the Thread constructor.
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                // Start the new thread. This causes the run method in the ClientHandler class to be called.
                clientThread.start();
            }
        // Catch any IOExceptions that may occur and log the error message.
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in the server: " + e.getMessage(), e);
        }
    }

    // This is a private static inner class named ClientHandler that implements the Runnable interface.
    private static class ClientHandler implements Runnable {
        // Declare a Socket object.
        private final Socket clientSocket;

        // The constructor for the ClientHandler class. It takes a Socket object as a parameter.
        public ClientHandler(Socket clientSocket) {
            // Assign the passed Socket object to the socket field.
            this.clientSocket = clientSocket;
        }

        // The run method, which is called when the thread is started.
        @Override
        public void run() {
            // A try-catch block to handle exceptions.
            try {
                // Create a BufferedReader object to read input from the client.
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // Create a PrintWriter object to send output to the client.
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Declare a String variable to hold the incoming message.
                String incomingMessage;
                // A loop that continues as long as there is input from the client.
                while ((incomingMessage = reader.readLine()) != null) {
                    // Log the received message.
                    LOGGER.log(Level.INFO, "Received message from " + clientSocket.getInetAddress() + ": " + incomingMessage);
                    // Broadcast the message to all connected clients. The actual broadcasting logic is not implemented in this code.
                    // As an example, the server echoes the message back to the same client.
                    writer.println("Server: " + incomingMessage);
                }

            // Catch any IOExceptions that may occur and log the error message.
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error handling client: " + e.getMessage(), e);
            } finally {
                // In a finally block, attempt to close the client socket and log a message indicating that the client has disconnected.
                try {
                    clientSocket.close();
                    LOGGER.log(Level.INFO, "Client disconnected: " + clientSocket.getInetAddress());
                // Catch any IOExceptions that may occur and log the error message.
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error closing client socket: " + e.getMessage(), e);
                }
            }
        }
    }
}
