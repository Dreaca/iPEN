import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("192.168.84.20", 8000);
        System.out.println("Connected to server");

        // Receive challenge from server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String challenge = reader.readLine();
        System.out.println("Server challenge: " + challenge);

        // Send decryption key to server
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(KeyGen.generateKey());

        // Receive response from server
        String response = reader.readLine();
        System.out.println("Server response: " + response);

        if (response.equals("Access granted")) {
            System.out.println("Connected to server successfully");
            // Continue with the connection
        } else {
            System.out.println("Access denied");
            socket.close();
        }
    }
}