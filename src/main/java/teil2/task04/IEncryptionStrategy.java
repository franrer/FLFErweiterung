package teil2.task04;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;

public interface IEncryptionStrategy {
    String encrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;

    String decrypt(String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;
}
