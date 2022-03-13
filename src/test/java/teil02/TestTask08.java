package teil02;

import lights.LedLight;
import lights.LightColor;
import lights.Type;
import mixingUnit.FoamTank;
import mixingUnit.WaterTank;
import org.junit.jupiter.api.*;
import teil2.task08.TankLed;
import teil2.task08.TankSensor;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask08 {

    private FoamTank foamTank;
    private WaterTank waterTank;
    private TankLed waterLED;
    private TankLed foamLED;

    @BeforeEach
    public void setup() {
        TankSensor waterSensor = new TankSensor();
        TankSensor foamSensor = new TankSensor();
        LedLight temp = new LedLight(2, LightColor.WHITE, Type.SENSORLIGHT);
        waterLED = new TankLed(temp);
        temp = new LedLight(2, LightColor.WHITE, Type.SENSORLIGHT);
        foamLED = new TankLed(temp);
        waterSensor.addListener(waterLED);
        foamSensor.addListener(foamLED);
        foamTank = new FoamTank(10, 10, 10, foamSensor);
        waterTank = new WaterTank(10, 10, 10, waterSensor);
    }


    @Test
    @Order(1)
    public void buildComplete() {
        assertNotNull(foamTank);
        assertNotNull(foamTank.getSensor());
        assertNotNull(waterTank);
        assertNotNull(waterTank.getSensor());
        assertNotNull(waterLED);
        assertNotNull(waterLED.getLed());
        assertNotNull(foamLED);
        assertNotNull(foamLED.getLed());
    }

    @Test
    @Order(2)
    public void functionTest() {
        foamTank.fillToMax();
        waterTank.fillToMax();
        for (int i = 100; i > 0; i--) {
            foamTank.takeOut(foamTank.maxCapacity() / 100);
            waterTank.takeOut(waterTank.maxCapacity() / 100);
            tankLedTest(((double) foamTank.occupiedSpace() / (double) foamTank.maxCapacity()) * 100, foamLED);
            tankLedTest(((double) waterTank.occupiedSpace() / (double) waterTank.maxCapacity()) * 100, waterLED);
        }
    }

    public void tankLedTest(double percent, TankLed led) {
        if (percent <= 10) {
            assertTrue(led.getLed().isOn());
            assertEquals(LightColor.RED, led.getLed().getColor());
        } else if (percent <= 25) {
            assertTrue(led.getLed().isOn());
            assertEquals(LightColor.ORANGE, led.getLed().getColor());

        } else if (percent <= 50) {
            assertTrue(led.getLed().isOn());
            assertEquals(LightColor.YELLOW, led.getLed().getColor());

        } else {
            assertFalse(led.getLed().isOn());
            assertEquals(LightColor.WHITE, led.getLed().getColor());

        }
    }
}
