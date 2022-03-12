package teil02;

import driveUnit.electrical.BatteryBox;
import driveUnit.electrical.BatteryManagement;
import org.junit.jupiter.api.*;
import teil2.task03.CellBattery;
import teil2.task03.IBattery;
import teil2.task05.LoadingStation;
import teil2.task05.Pin;
import teil2.task05.ThreeToOneAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask05 {

    private BatteryManagement batteryManagement;
    private LoadingStation loadingStation;
    private ThreeToOneAdapter threeToOneAdapter;

    @BeforeEach
    public void setup() {
        IBattery[][] batteries=new IBattery[2][2];
        batteries[0][0] = new CellBattery(100, 100, 10);
        batteries[0][1] = new CellBattery(100, 100, 10);
        batteries[1][0] = new CellBattery(100, 100, 10);
        batteries[1][1] = new CellBattery(100, 100, 10);
        BatteryBox batteryBox=new BatteryBox(batteries);
        batteryManagement=BatteryManagement.INSTANCE;
        batteryManagement.setBatteryBox(batteryBox);
        loadingStation=new LoadingStation(new Pin());
        threeToOneAdapter = new ThreeToOneAdapter(new Pin(), new Pin(), new Pin(), batteryManagement);
        }


    @Test
    @Order(1)
    public void buildComplete() {
        assertNotNull(batteryManagement);
        assertNotNull(batteryManagement.getBatteryBox());
        for(IBattery[] battery:batteryManagement.getBatteryBox().getBatteries()){
            for(IBattery cellBattery:battery){
                TestTask03.cellBatteryTest(cellBattery);
            }
        }
        assertNotNull(loadingStation);
        assertNotNull(threeToOneAdapter);
        assertNotNull(loadingStation.getPin());
        assertNotNull(threeToOneAdapter.getPin1());
        assertNotNull(threeToOneAdapter.getPin2());
        assertNotNull(threeToOneAdapter.getPin3());
        assertNotNull(threeToOneAdapter.getBatteryManagement());
    }

    @Test
    @Order(2)
    public void functionTest() {
        loadingStation.setEnergyAmount(1000);
        loadingStation.setLoadable(threeToOneAdapter);
        loadingStation.load();
        assertEquals(threeToOneAdapter,loadingStation.getLoadable());
        assertEquals(batteryManagement,threeToOneAdapter.getBatteryManagement());
        assertEquals(1000,loadingStation.getPin().getEnergyAmount());
        assertEquals(300,threeToOneAdapter.getPin1().getEnergyAmount());
        assertEquals(300,threeToOneAdapter.getPin2().getEnergyAmount());
        assertEquals(400,threeToOneAdapter.getPin3().getEnergyAmount());
        assertEquals(1000,batteryManagement.lookAmount());
    }


}
