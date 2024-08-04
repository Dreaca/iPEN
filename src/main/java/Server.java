// Server.java

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        String ipAddress = IpReader.getIPAddress(); // original IP address

        String encryptedIpAddress = Encrypter.encrypt(ipAddress);
        System.out.println("Encrypted IP address: " + encryptedIpAddress);

        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started. Waiting for a client...");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Read data from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                System.out.println("Client says: " + message);

                // Send response back to client
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println("Hello from server!");

                // Close the socket
                socket.close();
            } catch (IOException e) {
                System.out.println("Error handling client connection: " + e.getMessage());
            }
        }
    }
}