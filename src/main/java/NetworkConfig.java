import java.io.IOException;
public class NetworkConfig {
    public static void applyIPAddress(String ipAddress) {
        try {
            String command = "netsh interface ip set address name=\"Ethernet\" static " + ipAddress + " 255.255.255.0";


            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("IP address successfully configured to: " + ipAddress);
            } else {
                System.err.println("Failed to configure IP address. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

