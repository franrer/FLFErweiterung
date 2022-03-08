package teil02;

import org.junit.jupiter.api.*;
import teil2.task03.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask03 {
    private IBattery cellBattery;

    @BeforeEach
    public void setup() {
        cellBattery = new CellBattery(100,100,10);
    }

    @Test
    @Order(1)
    public void buildComplete() {
        cellBatteryTest(cellBattery);
    }
    public static void cellBatteryTest(IBattery cellBattery){
        assertNotNull(cellBattery);
        assertEquals(100000,cellBattery.maxCapacity());
        assertSame(CellBattery.class,cellBattery.getClass());
        CellBattery cellB=(CellBattery)cellBattery;
        assertNotNull(cellB.getUnitList());
        assertSame(MainCell[].class,cellB.getUnitList().getClass());
        assertNotNull(cellB.getUnitList()[0].getUnitList());
        assertSame(SubCell[].class,cellB.getUnitList()[0].getUnitList().getClass());
        assertNotNull(cellB.getUnitList()[0].getUnitList()[0].getUnitList());
        assertSame(Cell[].class,cellB.getUnitList()[0].getUnitList()[0].getUnitList().getClass());
        assertNull(cellB.getUnitList()[0].getUnitList()[0].getUnitList()[0].getUnitList());
        assertEquals(100,cellB.getUnitList().length);
        assertEquals(100,cellB.getUnitList()[0].getUnitList().length);
        assertEquals(10,cellB.getUnitList()[0].getUnitList()[0].getUnitList().length);
    }

    @Test
    @Order(2)
    public void functionTest(){
        assertEquals(0,cellBattery.occupiedSpace());
        assertEquals(100000,cellBattery.emptySpace());
        cellBattery.charge(10000);
        assertEquals(10000,cellBattery.occupiedSpace());
        assertEquals(90000,cellBattery.emptySpace());
        cellBattery.takeOut(5000);
        assertEquals(5000,cellBattery.occupiedSpace());
        assertEquals(95000,cellBattery.emptySpace());
        cellBattery.fillToMax();
        assertEquals(100000,cellBattery.occupiedSpace());
        assertEquals(0,cellBattery.emptySpace());
    }
}
