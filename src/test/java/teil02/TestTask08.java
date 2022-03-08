package teil02;

import driveUnit.electrical.BatteryBox;
import driveUnit.electrical.BatteryManagement;
import mixingUnit.FoamTank;
import mixingUnit.WaterTank;
import org.junit.jupiter.api.*;
import teil2.task03.CellBattery;
import teil2.task03.IBattery;
import teil2.task05.LoadingStation;
import teil2.task05.Pin;
import teil2.task05.ThreeToOneAdapter;
import teil2.task08.TankLed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask08 {

    private FoamTank foamTank;
    private WaterTank waterTank;
    private TankLed waterLED;
    private TankLed foamLED;

    @BeforeEach
    public void setup() {

    }


    @Test
    @Order(1)
    public void buildComplete() {

    }

    @Test
    @Order(2)
    public void functionTest() {

    }
}
