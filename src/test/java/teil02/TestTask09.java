package teil02;

import FLF.FLF;
import ccu.CCU;
import org.junit.jupiter.api.*;
import teil1.MainTest;
import teil2.task09.IUnitToTest;
import teil2.task09.Tester;
import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask09 {
    private FLF flf;
    private MainTest test;

    @BeforeEach
    public void setup() {
        flf = new FLF.Builder(false).buildTart2();
        flf.getMixingUnit().fillTanks();
    }

    @Test
    @Order(1)
    public void buildComplete() {
        //MainTest.buildTest(flf);
        FrontTurret front = (FrontTurret) flf.getCabin().getDriverSection().getJoystick().getTurret();
        ;
        RoofTurret roof = (RoofTurret) flf.getCabin().getOperatorSection().getJoystick().getTurret();
        CCU ccu = flf.getCabin().getDriverSection().getCcu();
        List<IUnitToTest> units = ccu.getUnitsToTest();
        assertNotNull(units);
        assertTrue(units.contains(front));
        assertTrue(units.contains(roof));
        for (FloorSprayNozzle nuzzle : ccu.getFloorSprayNozzle()) {
            assertTrue(units.contains(nuzzle));
        }
    }

    @Test
    @Order(2)
    public void functionTest() {
        flf.getCabin().getOperatorSection().getControlpanel().getMotorSwitch().turnSwitch();
        Tester tester = flf.getCabin().getDriverSection().getCcu().getTester();
        assertTrue(tester.getFrontTurretTestResult());
        assertTrue(tester.getRoofTurretTestResult());
        for (boolean result : tester.getNozzleTestResult()) {
            assertTrue(result);
        }

    }


}
