package teil02;

import FLF.FLF;
import cabin.operatorSection.Controlpanel;
import ccu.CCU;
import driveUnit.DriveUnit;
import driveUnit.electrical.BatteryManagement;
import org.junit.jupiter.api.*;
import teil1.MainTest;
import teil2.task03.CellBattery;
import teil2.task03.IBattery;
import turrets.FloorSprayNozzle;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTestTeil2 extends MainTest {
    @BeforeEach
    @Override
    public void setup() {
        flf = new FLF.Builder(false).buildTart2();
        flf.getMixingUnit().fillTanks();
    }

    @Test
    @Override
    @Order(1)
    public void buildComplete() {
        buildTest(flf);
        buildExtension(flf);
        flf.getMixingUnit().fillTanks();
    }

    public static void buildExtension(FLF flf) {
        Controlpanel panel = flf.getCabin().getOperatorSection().getControlpanel();
        assertNotNull(panel.getFoamLed());
        assertNotNull(panel.getWaterLed());
        BatteryManagement management = ((DriveUnit) flf.getDriveUnit()).getBatteryManagement();
        assertNotNull(management.getThreePinConnector());
        for (IBattery[] row : management.getBatteryBox().getBatteries()) {
            for (IBattery slot : row) {
                assertEquals(CellBattery.class, slot.getClass());
            }
        }
        CCU ccu = ((DriveUnit) flf.getDriveUnit()).getCcu();
        assertTrue(ccu.getUnitsToTest().contains(ccu.getDriverSection().getJoystick().getTurret()));
        assertTrue(ccu.getUnitsToTest().contains(ccu.getOperatorSection().getJoystick().getTurret()));
        for (FloorSprayNozzle nozzle : ccu.getFloorSprayNozzle()) {
            assertTrue(ccu.getUnitsToTest().contains(nozzle));
        }

    }
}
