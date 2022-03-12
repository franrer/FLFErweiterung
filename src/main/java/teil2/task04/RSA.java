package teil2.task04;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RSA implements IEncryptionStrategy {
    private static Cipher ecipher;
    private static Cipher dcipher;

    private static KeyPair key;


    public RSA() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        key = KeyPairGenerator.getInstance("RSA").generateKeyPair();

        ecipher = Cipher.getInstance("RSA");
        dcipher = Cipher.getInstance("RSA");

        ecipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        dcipher.init(Cipher.DECRYPT_MODE, key.getPrivate());

    }

    @Override
    public String encrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        byte[] enc = ecipher.doFinal(s.getBytes(StandardCharsets.UTF_8));

        enc = Base64.getEncoder().encode(enc);

        return new String(enc);

    }

    @Override
    public String decrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        byte[] utf8 = s.getBytes(StandardCharsets.UTF_8);

        byte[] dec = Base64.getDecoder().decode(utf8);

        return new String(dcipher.doFinal(dec));
    }
}
