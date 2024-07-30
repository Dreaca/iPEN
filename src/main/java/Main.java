public class Main {
    public static void main(String[] args) {
        if (PermissionPrompt.askForPermission()) {
            String ipAddress = IpReader.getIPAddress();
            if (ipAddress != null) {
                try {
                    String encryptedIPAddress = Encrypter.encrypt(ipAddress);
                    System.out.println("Encrypted IP Address: " + encryptedIPAddress);
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
