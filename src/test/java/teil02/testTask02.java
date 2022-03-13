package teil02;

import FLF.FLF;
import cabin.operatorSection.Controlpanel;
import ccu.CCU;
import lights.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testTask02 {
    private FLF flf;
    private CCU ccu;
    private CCU turnSwitch;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
    }

    @Test
    public void task02() {
        flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
        assertFalse(flf.getDriveUnit().getMotorState());
        flf.getCabin().getDriverSection().pressSwitch(SwitchType.electroMotor);
        assertFalse(flf.getDriveUnit().getMotorState());
        lightSetup(true, true, true, true, true);
        lightState(true, true, true, true, true);
        lightSetup(true, true, true, true, true);
        lightState(false, false, false, false, false);
    }

    private void lightState(boolean roofLight, boolean sideLight, boolean frontLight, boolean warningLight, boolean blueLight) {
        for (Light l : flf.getLights()) {
            switch (l.getType()) {
                case BLUELIGHT -> assertEquals(blueLight, l.isOn());
                case WARNINGLIGHT -> assertEquals(warningLight, l.isOn());
                case SPOTLIGHT -> {
                    switch (l.getSide()) {
                        case ROOF -> assertEquals(roofLight, l.isOn());
                        case FRONT -> assertEquals(frontLight, l.isOn());
                        case LEFT, RIGHT, SIDE -> assertEquals(sideLight, l.isOn());
                        default -> {
                        }
                    }
                }
            }
        }
        Controlpanel p = flf.getCabin().getOperatorSection().getControlpanel();
        assertEquals(blueLight, p.getBlueLightSwitch().isOn());
        assertEquals(warningLight, p.getWarningLightSwitch().isOn());
        assertEquals(frontLight, p.getFrontLightSwitch().isOn());
        assertEquals(sideLight, p.getSideLightSwitch().isOn());
        assertEquals(roofLight, p.getRoofLightSwitch().isOn());
    }

    private void lightSetup(boolean roofLight, boolean sideLight, boolean frontLight, boolean warningLight, boolean blueLight) {
        Controlpanel p = flf.getCabin().getOperatorSection().getControlpanel();
        if (blueLight)
            p.getBlueLightSwitch().turnSwitch();

        if (warningLight)
            p.getWarningLightSwitch().turnSwitch();

        if (frontLight)
            p.getFrontLightSwitch().turnSwitch();

        if (sideLight)
            p.getSideLightSwitch().turnSwitch();

        if (roofLight)
            p.getRoofLightSwitch().turnSwitch();
    }

}

