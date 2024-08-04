import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.252.126", 8000);
            System.out.println("Connected to server");

            // Receive challenge from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String challenge = reader.readLine();
            System.out.println("Server : " + challenge);

            // Send decryption key to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("3Zs7z/42FnEsT0o+1z0xfg==");

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
        } catch (SocketException e) {
            // Handle connection reset error
            JOptionPane.showMessageDialog(null, "Connection reset by server. Please try again.");
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }
}