package teil02;

import FLF.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import teil2.task06.OffState;
import teil2.task06.OnState;
import teil2.task06.OperatorButton;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask06 {

    private FLF flf;
    OperatorButton motor;
    OperatorButton blueLight;
    OperatorButton warningLight;
    OperatorButton headLight;
    OperatorButton roofLight;
    OperatorButton sideLight;
    OperatorButton nozzle;


    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
        flf.getMixingUnit().fillTanks();
        motor = new OperatorButton(SwitchType.electroMotor, flf.getCabin().getOperatorSection().getCcu());
        blueLight = new OperatorButton(SwitchType.BlueLights, flf.getCabin().getOperatorSection().getCcu());
        warningLight = new OperatorButton(SwitchType.warningLights, flf.getCabin().getOperatorSection().getCcu());
        headLight = new OperatorButton(SwitchType.headLightsFront, flf.getCabin().getOperatorSection().getCcu());
        roofLight = new OperatorButton(SwitchType.headLightsRoof, flf.getCabin().getOperatorSection().getCcu());
        sideLight = new OperatorButton(SwitchType.SideLights, flf.getCabin().getOperatorSection().getCcu());
        nozzle = new OperatorButton(SwitchType.floorSprayNozzles, flf.getCabin().getOperatorSection().getCcu());

    }

    @Test
    public void Task06() {
        assertEquals(OffState.class, motor.getState().getClass());
        assertEquals(OffState.class, blueLight.getState().getClass());
        assertEquals(OffState.class, warningLight.getState().getClass());
        assertEquals(OffState.class, headLight.getState().getClass());
        assertEquals(OffState.class, roofLight.getState().getClass());
        assertEquals(OffState.class, sideLight.getState().getClass());
        assertEquals(OffState.class, motor.getState().getClass());
        motor.buttonPress();
        blueLight.buttonPress();
        warningLight.buttonPress();
        headLight.buttonPress();
        roofLight.buttonPress();
        sideLight.buttonPress();
        nozzle.buttonPress();
        assertEquals(OnState.class, motor.getState().getClass());
        assertEquals(OnState.class, blueLight.getState().getClass());
        assertEquals(OnState.class, warningLight.getState().getClass());
        assertEquals(OnState.class, headLight.getState().getClass());
        assertEquals(OnState.class, roofLight.getState().getClass());
        assertEquals(OnState.class, sideLight.getState().getClass());
        assertEquals(OnState.class, motor.getState().getClass());
        motor.buttonPress();
        blueLight.buttonPress();
        warningLight.buttonPress();
        headLight.buttonPress();
        roofLight.buttonPress();
        sideLight.buttonPress();
        nozzle.buttonPress();
        assertEquals(OffState.class, motor.getState().getClass());
        assertEquals(OffState.class, blueLight.getState().getClass());
        assertEquals(OffState.class, warningLight.getState().getClass());
        assertEquals(OffState.class, headLight.getState().getClass());
        assertEquals(OffState.class, roofLight.getState().getClass());
        assertEquals(OffState.class, sideLight.getState().getClass());
        assertEquals(OffState.class, motor.getState().getClass());
    }

}


