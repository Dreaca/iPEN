import java.net.*;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Server {
    public static void main(String[] args) throws Exception {
        String ipAddress = "192.168.84.20"; // server's IP address
        SecretKeySpec secretKey = (SecretKeySpec) KeyGen.generateKey();
        String encryptedIpAddress = Encrypter.encrypt(ipAddress, secretKey);
        System.out.println("Encrypted IP address: " + encryptedIpAddress);

        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started. Waiting for client connection...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        // Send challenge to client
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("Please provide decryption key");

        // Receive decryption key from client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String decryptionKey = reader.readLine();
        System.out.println("Decryption key: " + decryptionKey);

// Convert the decryption key string to a byte array
        byte[] decryptionKeyBytes = decryptionKey.getBytes();

// Create a SecretKeySpec from the decryption key bytes
        SecretKeySpec secretKeySpec = new SecretKeySpec(decryptionKeyBytes, "AES");

// Attempt to decrypt IP address using provided decryption key
        String decryptedIpAddress = Decrypter.decrypt(encryptedIpAddress, secretKeySpec);

        if (decryptedIpAddress!= null && decryptedIpAddress.equals(ipAddress)) {
            System.out.println("Decryption successful. Granting access to client");
            writer.println("Access granted");
            // Continue with the connection
        } else {
            System.out.println("Decryption failed. Terminating connection");
            socket.close();
        }
    }
}