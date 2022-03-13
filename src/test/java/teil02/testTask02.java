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

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testTask02 {
    private FLF flf;
    private CCU ccu;
    private CCU turnSwitch;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
    }
    public void set(){ ccu = new CCU(new CCU(turnSwitch));}

    @Test
    public void task02() {
        Seat seat = flf.getCabin().getOperatorSection().getSeat();;
        {

            flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getMotors().isStarted());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
            for (int i = 0; i < 2; i++) {
                assertFalse(flf.getCCU().getMotors().isStarted());
            }
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.warningLights);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.BlueLights);
            ccu.turnSwitch(true);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.warningLights);
            flf.getCabin().getDriverSection().pressSwitch(SwitchType.BlueLights);
            ccu.turnSwitch(false);
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
    }

