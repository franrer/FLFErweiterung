package teil02;

import FLF.FLF;
import cabin.Seat;
import cabin.driverSection.DriverSection;
import ccu.CCU;
import complex1.Person;
import org.junit.jupiter.api.*;
import teil2.task02.*;
import lights.Light;
import lights.LED;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testTask02 {
    private FLF flf;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
    }

    @Test
    public void task02() {
        Seat seat = flf.getCabin().getOperatorSection().getSeat();;
        {

            flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
            for (int i = 0; i < 2; i++) {
                assertTrue(flf.getCCU().getMotors()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getMotors()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.warningLights);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.BlueLights);
            CheckLED(true);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.warningLights);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.BlueLights);
            CheckLED(false);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.headLightsFront);
            for (int i = 0; i < 2; i++) {
                assertTrue(flf.getCCU().getHeadFrontLights()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.headLightsFront);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getHeadFrontLights()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.headLightsRoof);
            for (int i = 0; i < 2; i++) {
                assertTrue(flf.getCCU().getHeadRoofLights()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.headLightsRoof);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getHeadRoofLights()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.SideLights);
            for (int i = 0; i < 2; i++) {
                assertTrue(flf.getCCU().getSideLights()[i].isOn());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.SideLights);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getSideLights()[i].isOn());
            }
        }
        
    }

    private void CheckLED(boolean b) {
    }
}