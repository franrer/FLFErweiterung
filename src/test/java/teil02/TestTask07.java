package teil02;

import FLF.FLF;
import lights.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import teil2.task06.OperatorButton;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask07 {
    private FLF flf;
    OperatorButton motor;
    OperatorButton blueLight;
    OperatorButton warningLight;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
        motor = new OperatorButton(SwitchType.electroMotor, flf.getCabin().getOperatorSection().getCcu());
        blueLight = new OperatorButton(SwitchType.BlueLights, flf.getCabin().getOperatorSection().getCcu());
        warningLight = new OperatorButton(SwitchType.warningLights, flf.getCabin().getOperatorSection().getCcu());

    }

    @Test
    public void Task06() {
        blueLight.buttonPress();
        lightState(false,false,false,false,true);
        blueLight.buttonPress();
        lightState(false,false,false,false,false);

        warningLight.buttonPress();
        lightState(false,false,false,true,false);
        warningLight.buttonPress();
        lightState(false,false,false,false,false);

        assertFalse(flf.getDriveUnit().getMotorState());
        motor.buttonPress();
        assertTrue(flf.getDriveUnit().getMotorState());
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
    }
}
