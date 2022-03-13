package teil02;

import FLF.FLF;
import cabin.driverSection.DriverSection;
import complex1.IDCard;
import complex1.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import teil2.task03.CellBattery;
import teil2.task03.IBattery;
import teil2.task06.SwitchType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask06 {

        private FLF flf;

    @BeforeEach
        public void setup() {
            flf = new FLF(new FLF.Builder(false));
        }


        @Test
        public void Task06() {
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


