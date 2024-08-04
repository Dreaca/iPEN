import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGen {
    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // 128-bit key size
        SecretKey key = keyGen.generateKey();
        System.out.println(key);
        return key;
    }

    public static byte[] getKeyBytes(SecretKey key) {
        return key.getEncoded();
    }
}