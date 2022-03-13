package teil02;

import FLF.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask07 {
    private FLF flf;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
    }

    @Test
    public void Task07()
    {
        assertFalse(flf.getDriveUnit().getMotorState());
        assertFalse(flf.getDriveUnit().getMotorState());
        flf.getCabin().getSeats();
        flf.getCabin().getSeats().pressSwitch(SwitchType.electroMotor);
        for (int i = 0; i < 2; i++) {
            assertFalse(flf.getDriveUnit().getMotorState());
        }
        flf.getCabin().getSeats().pressSwitch(SwitchType.electroMotor);
        for (int i = 0; i < 2; i++) {
            assertFalse(flf.getDriveUnit().getMotorState());
        }
    }
}
