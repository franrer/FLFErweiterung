package teil02;

import FLF.FLF;
import cabin.operatorSection.Controlpanel;
import org.junit.jupiter.api.*;
import teil1.MainTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTestTeil2 extends MainTest {
    @BeforeEach
    @Override
    public void setup() {
        flf = new FLF.Builder(false).buildTart2();
    }
    @Test
    @Override
    @Order(1)
    public void buildComplete() {
        buildTest(flf);
        buildExtension(flf);
    }
    public static void buildExtension(FLF flf){
        Controlpanel panel=flf.getCabin().getOperatorSection().getControlpanel();
        assertNotNull(panel.getFoamLed());
        assertNotNull(panel.getWaterLed());
    }
}
