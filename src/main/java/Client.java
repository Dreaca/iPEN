// Client.java

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        String ipAddress = "192.168.84.20"; // original IP address

        String encryptedIpAddress = Encrypter.encrypt(ipAddress);
        System.out.println("Encrypted IP address: " + encryptedIpAddress);

        String decryptedIpAddress = Decrypter.decrypt(encryptedIpAddress);
        System.out.println("Decrypted IP address: " + decryptedIpAddress);

        try {
            Socket socket = new Socket(decryptedIpAddress, 8000);
            System.out.println("Connected to server");

            // Send data to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello from client!");

            // Read response from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println("Server says: " + message);

            // Close the socket
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Failed to connect to server. IP address may be encrypted or invalid.");
        } catch (IOException e) {
            System.out.println("Failed to connect to server. IP address may be encrypted or invalid.");
            System.out.println("Error message: " + e.getMessage());
        }
    }
}