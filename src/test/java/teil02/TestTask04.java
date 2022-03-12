package teil02;

import FLF.FLF;
import complex1.DES;
import org.junit.jupiter.api.*;
import teil2.task04.AES;
import teil2.task04.CentralConfiguration;
import teil2.task04.IEncryptionStrategy;
import teil2.task04.RSA;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask04 {
    private IEncryptionStrategy des;
    private IEncryptionStrategy aes;
    private IEncryptionStrategy rsa;

    @BeforeEach
    public void setup() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            des = new DES();
            aes = new AES();
            rsa = new RSA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void buildComplete() {
        assertNotNull(des);
        assertNotNull(aes);
        assertNotNull(rsa);
    }


    @Test
    @Order(2)
    public void functionTestDES() {
        try {
            String testString = "test";
            String encryptTestString = des.encrypt(testString);
            String decryptTestString = des.decrypt(encryptTestString);
            assertEquals(testString, decryptTestString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void functionTestAES() {
        try {
            String testString = "test";
            String encryptTestString = aes.encrypt(testString);
            String decryptTestString = aes.decrypt(encryptTestString);
            assertEquals(testString, decryptTestString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void functionTestRSA() {
        try {
            String testString = "test";
            String encryptTestString = rsa.encrypt(testString);
            String decryptTestString = rsa.decrypt(encryptTestString);
            assertEquals(testString, decryptTestString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configTest() {
        FLF flf = new FLF(new FLF.Builder(false));
        assertEquals(DES.class, flf.getCabin().getOperatorSection().getCcu().getEncryptionStrategy().getClass());
        CentralConfiguration.instance.encryptionStrategy = "AES";
        flf = new FLF(new FLF.Builder(false));
        assertEquals(AES.class, flf.getCabin().getOperatorSection().getCcu().getEncryptionStrategy().getClass());
        CentralConfiguration.instance.encryptionStrategy = "RSA";
        flf = new FLF(new FLF.Builder(false));
        assertEquals(RSA.class, flf.getCabin().getOperatorSection().getCcu().getEncryptionStrategy().getClass());
    }

}
