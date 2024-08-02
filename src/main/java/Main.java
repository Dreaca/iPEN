public class Main {
    public static void main(String[] args) {
        if (PermissionPrompt.askForPermission()) {
            String ipAddress = IpReader.getIPAddress();
            if (ipAddress != null) {
                try {
                    String encryptedIPAddress = Encrypter.encrypt(ipAddress);
                    System.out.println("Encrypted IP Address: " + encryptedIPAddress);

                    NetworkConfig.applyIPAddress(encryptedIPAddress);
                    String decryptedIPAddress = Decrypter.decrypt(encryptedIPAddress);
                    System.out.println("Decrypted IP Address: " + decryptedIPAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Could not retrieve IP address.");
            }
        } else {
            System.out.println("Permission denied.");
        }
    }
}
