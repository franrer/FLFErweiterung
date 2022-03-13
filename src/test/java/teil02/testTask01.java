package teil02;

import org.junit.jupiter.api.*;
import teil2.task01.Communicator;
import turrets.turretsWithFoam.FrontTurret;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testTask01 {
    private Communicator communicator;

    @BeforeEach
    public void setup() {
        communicator = new Communicator();
    }

    @Test
    @Order(1)
    public void buildComplete() {
        assertNotNull(communicator);
        assertNotNull(communicator.getPort());
    }


    @Test
    @Order(2)
    public void functionTest() {
        FrontTurret turret = new FrontTurret();
        communicator.fillTanks();
        int[] amountInTank = communicator.getTanksFillState();
        int[] maxAmountInTank = communicator.getTanksCapacity();
        assertEquals(amountInTank[0], maxAmountInTank[0]);
        assertEquals(amountInTank[1], maxAmountInTank[1]);
        communicator.takeOut(turret);
        amountInTank = communicator.getTanksFillState();
        int water = maxAmountInTank[0] - turret.getTurretOutput();
        assertEquals(water, amountInTank[0]);
        turret.increaseFoam();
        communicator.mixing(turret);

        amountInTank = communicator.getTanksFillState();
        int foam = maxAmountInTank[1] - (int) ((double) turret.getTurretOutput() * (double) turret.getFoam() / 100);
        water = water - ((int) ((1 - (double) turret.getFoam() / 100) * turret.getTurretOutput()));
        assertEquals(water, amountInTank[0]);
        assertEquals(foam, amountInTank[1]);
    }
}
