package complex1;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DES {

    private static Cipher ecipher;
    private static Cipher dcipher;

    private static SecretKey key;


    public DES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        key = KeyGenerator.getInstance("DES").generateKey();

        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");

        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);

    }


    public String encrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        byte[] enc = ecipher.doFinal(s.getBytes(StandardCharsets.UTF_8));

        enc = Base64.getEncoder().encode(enc);

        return new String(enc);

    }

    public String decrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        byte[] utf8 = s.getBytes(StandardCharsets.UTF_8);

        byte[] dec = Base64.getDecoder().decode(utf8);

        return new String(dcipher.doFinal(dec));

    }


}
