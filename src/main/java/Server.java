import java.net.*;
import java.io.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Server {
    public static void main(String[] args) throws Exception {
        if(PermissionPrompt.askForPermission()){

            String ipAddress = IpReader.getIPAddress(); // server's IP address
            SecretKeySpec secretKey = (SecretKeySpec) KeyGen.generateKey();
            String keyBytes1 = KeyGen.getKeyBytes(secretKey);

            System.out.println("The Key is : "+keyBytes1);
            String encryptedIpAddress = Encrypter.encrypt(ipAddress, secretKey);
            System.out.println("Encrypted IP address: " + encryptedIpAddress);

            String decryptedIpAddress1 = Decrypter.decrypt(encryptedIpAddress, secretKey);
            System.out.println("Decrypted IP address: " + decryptedIpAddress1);

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

            String base64Key = decryptionKey;

            byte[] keyBytes = Base64.getDecoder().decode(base64Key);

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            try {
                String decryptedIpAddress = Decrypter.decrypt(encryptedIpAddress, secretKeySpec);

                if (decryptedIpAddress!= null && decryptedIpAddress.equals(ipAddress)) {
                    System.out.println("Decryption successful. Granting access to client");
                    writer.println("Access granted");
                    // Continue with the connection
                } else {
                    System.out.println("Decryption failed. Terminating connection");
                    socket.close();
                }
            } catch (BadPaddingException e) {
                // Handle bad padding exception
                System.out.println("Error: Bad padding. Decryption failed.");
                writer.println("Access denied");
                socket.close();
            } catch (Exception e) {
                // Handle other exceptions
                e.printStackTrace();
            }
        }
    }
}