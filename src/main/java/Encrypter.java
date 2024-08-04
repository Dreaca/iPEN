import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;

class Encrypter {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = new byte[]{ 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    static SecretKey key;
    public static String encrypt(String data) throws Exception {
        key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    private static SecretKey generateKey() {
        return new SecretKeySpec(keyValue, ALGORITHM);
    }
}