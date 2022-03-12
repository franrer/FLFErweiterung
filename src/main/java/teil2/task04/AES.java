package teil2.task04;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES implements IEncryptionStrategy{
    private  Cipher ecipher;
    private  Cipher dcipher;

    private  SecretKey key;


    public AES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        key = KeyGenerator.getInstance("AES").generateKey();

        ecipher = Cipher.getInstance("AES");
        dcipher = Cipher.getInstance("AES");

        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);

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
