package teil2.task04;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;

public interface IEncryptionStrategy {
    String encrypt(String string) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;

    String decrypt(String string) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;
}
