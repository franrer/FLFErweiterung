package teil1;

import FLF.FLF;
import cabin.BusDoor;
import cabin.Cabin;
import cabin.driverSection.DriverSection;
import cabin.operatorSection.Controlpanel;
import cabin.operatorSection.OperatorSection;
import ccu.CCU;
import driveUnit.DriveUnit;
import driveUnit.ElectricEngine;
import driveUnit.mechanical.Axis;
import driveUnit.mechanical.SteeringAxis;
import inputs.switches.LightSwitch;
import inputs.switches.LightSwitchArea;
import inputs.switches.Switch;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.ButtonJoy;
import inputs.turretInputs.Joystick;
import inputs.turretInputs.RotaryKnob;
import lights.Light;
import lights.Position;
import mixingUnit.MixingUnit;
import mixingUnit.Tank;
import org.junit.jupiter.api.*;
import teil2.task03.IBattery;
import turrets.FloorSprayNozzle;
import turrets.Turret;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;
import turrets.turretsWithFoam.Segment;
import turrets.turretsWithFoam.TurretWithFoam;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {
    protected FLF flf;

    @BeforeEach
    public void setup() {
        flf = new FLF(new FLF.Builder(false));
    }

    @Test
    @Order(1)
    public void buildComplete() {
        buildTest(flf);
    }

    public static void buildTest(FLF flf) {
        //flf
        assertNotNull(flf);
        assertNotNull(flf.getPowerUnit());
        assertNotNull(flf.getDriveUnit());
        assertNotNull(flf.getMixingUnit());
        assertNotNull(flf.getLights());

        //cabin
        assertNotNull(flf.getCabin());
        Cabin c = flf.getCabin();
        assertNotNull(c.getDriverSection());
        assertNotNull(c.getOperatorSection());
        assertNotNull(c.getBusDoorLeft());
        assertNotNull(c.getBusDoorRight());
        buildDoor(c.getBusDoorRight());
        buildDoor(c.getBusDoorLeft());

        //operator section
        OperatorSection o = flf.getCabin().getOperatorSection();
        assertNotNull(o.getCabin());
        assertNotNull(o.getControlpanel());
        assertNotNull(o.getCcu());
        assertNotNull(o.getJoystick());
        buildJoystick(o.getJoystick());
        assertNotNull(o.getRotaryKnob());
        buildRotaryKnob(o.getRotaryKnob());
        assertNotNull(o.getSeat());
        Assertions.assertFalse(o.getSeat().isOccupied());

        //CCU
        assertNotNull(flf.getCabin().getOperatorSection().getCcu());
        CCU ccu = flf.getCabin().getOperatorSection().getCcu();
        assertNotNull(ccu.getDriverSection());
        assertNotNull(ccu.getMixingUnit());
        assertNotNull(ccu.getLights());
        assertNotNull(ccu.getOperatorSection());
        assertNotNull(ccu.getDriveUnit());
        assertNotNull(ccu.getFloorSprayNozzle());

        //Controllpanel
        Controlpanel p = flf.getCabin().getOperatorSection().getControlpanel();
        assertNotNull(p.getRoofLightSwitch());
        buildSwitch(p.getRoofLightSwitch());
        assertNotNull(p.getSideLightSwitch());
        buildSwitch(p.getSideLightSwitch());
        assertNotNull(p.getFrontLightSwitch());
        buildSwitch(p.getFrontLightSwitch());
        assertNotNull(p.getWarningLightSwitch());
        buildSwitch(p.getWarningLightSwitch());
        assertNotNull(p.getBlueLightSwitch());
        buildSwitch(p.getBlueLightSwitch());
        assertNotNull(p.getMotorSwitch());
        buildSwitch(p.getMotorSwitch());
        assertNotNull(p.getOperatorSection());
        assertNotNull(p.getSelfProtection());
        buildSwitch(p.getSelfProtection());

        //driver Section
        DriverSection d = flf.getCabin().getDriverSection();
        assertNotNull(d.getAccelerator());
        assertNotNull(d.getAccelerator().getDriverSection());
        assertNotNull(d.getBrake());
        assertNotNull(d.getBrake().getDriverSection());
        assertNotNull(d.getCabin());
        assertNotNull(d.getBatteryDisplay());
        assertNotNull(d.getBatteryDisplay().getDriverSection());
        assertNotNull(d.getSpeedDisplay());
        assertNotNull(d.getSpeedDisplay().getDriverSection());
        assertNotNull(d.getJoystick());
        buildJoystick(d.getJoystick());
        assertNotNull(d.getRotaryKnob());
        buildRotaryKnob(d.getRotaryKnob());
        assertNotNull(d.getCcu());
        assertNotNull(d.getSeat());
        Assertions.assertFalse(d.getSeat().isOccupied());
        assertNotNull(d.getSteeringWheel());
        assertNotNull(d.getSteeringWheel().getDriverSection());

        //DriveUnit
        if (flf.getDriveUnit() instanceof DriveUnit dri) {
            assertNotNull(dri.getAxis());
            for (Axis a : dri.getAxis()) {
                buildAxis(a);
            }
            assertNotNull(dri.getSteeringAxes());
            for (SteeringAxis a : dri.getSteeringAxes()) {
                buildSteeringAxis(a);
            }
            assertNotNull(dri.getEngines());
            int amountEngines = 2;
            assertEquals(amountEngines, dri.getEngines().length);
            for (ElectricEngine e : dri.getEngines()) {
                buildEngine(e);
            }
            int stdSpeed = 0;
            assertEquals(stdSpeed, dri.getSpeed());

            assertNotNull(dri.getBatteryManagement());
            assertNotNull(dri.getBatteryManagement().getBatteryBox());
            assertNotNull(dri.getBatteryManagement().getBatteryBox().getBatteries());
            IBattery[][] batteries = dri.getBatteryManagement().getBatteryBox().getBatteries();
            int stdBoxLength = 2;
            int stdBoxWidth = 2;
            assertEquals(stdBoxLength, batteries.length);
            assertEquals(stdBoxWidth, batteries[0].length);
            int stdBatLength = 100;
            int stdBatWidth = 10;
            int stdBatHeight = 100;
            for (IBattery[] batteryLine : batteries) {
                for (IBattery bat : batteryLine) {
                    assertNotNull(bat);
                    /* old Batterys
                    assertNotNull(bat.getCapacity());
                    assertEquals(stdBatLength, bat.getCapacity().length);
                    assertEquals(stdBatWidth, bat.getCapacity()[0].length);
                    assertEquals(stdBatHeight, bat.getCapacity()[0][0].length);
                    */

                }
            }
        }

        //Turrets
        buildTurret(d.getJoystick().getTurret(), 500);
        buildTurret(o.getJoystick().getTurret(), 500);
        for (FloorSprayNozzle floor : ccu.getFloorSprayNozzle()) {
            buildTurret(floor, 100);
        }

        //Tank
        if (flf.getMixingUnit() instanceof MixingUnit mix) {
            assertNotNull(mix.getFoamTank());
            buildTank(mix.getFoamTank(), 75, 45, 10);
            assertNotNull(mix.getWaterTank());
            buildTank(mix.getWaterTank(), 75, 45, 30);
        }
        //Lichter
        Light[] l = flf.getLights();
        assertEquals(38, l.length);

    }

    public static void buildTurret(Turret t, int stdout) {
        assertEquals(stdout, t.getTurretOutput());
        if (t instanceof TurretWithFoam ft) {
            assertEquals(0, ft.getFoam());

            if (ft instanceof FrontTurret fr) {
                assertEquals(0, fr.getPosition());
            }
            if (ft instanceof RoofTurret ro) {
                assertEquals(0, ro.getPositionVertical());
                assertNotNull(ro.getSegments());
                assertEquals(3, ro.getSegments().size());
                ArrayList<Segment> s = ro.getSegments();
                assertNotNull(s.get(0));
                assertEquals(6, s.get(0).getLength());
                assertNotNull(s.get(1));
                assertEquals(6, s.get(1).getLength());
                assertNotNull(s.get(2));
                assertEquals(5, s.get(2).getLength());

            }
        }
    }

    public static void buildTank(Tank t, int length, int width, int height) {
        assertNotNull(t.getCapacity());
        assertEquals(length, t.getCapacity().length);
        assertEquals(width, t.getCapacity()[0].length);
        assertEquals(height, t.getCapacity()[0][0].length);
    }

    public static void buildEngine(ElectricEngine e) {
        assertNotNull(e.getPowerUnit());
        Assertions.assertFalse(e.isStarted());
        double stdConsumption = 12.5;
        assertEquals(stdConsumption, e.getConsumption());
    }

    public static void buildSteeringAxis(SteeringAxis axis) {
        int stdDirection = 0;
        assertEquals(stdDirection, axis.getDirection());
    }

    public static void buildAxis(Axis axis) {
        int amountTires = 2;
        int amountBrakeDiscs = 6;
        assertNotNull(axis.getWheels());
        for (int i = 0; i < axis.getWheels().length; i++) {
            assertNotNull(axis.getWheels()[i]);
        }
        assertEquals(amountTires, axis.getWheels().length);
        assertNotNull(axis.getBrakeDisc());
        assertEquals(amountBrakeDiscs, axis.getBrakeDisc().length);
        for (int i = 0; i < axis.getBrakeDisc().length; i++) {
            assertNotNull(axis.getBrakeDisc()[i]);
        }
    }

    public static void buildSwitch(Switch s) {
        Assertions.assertFalse(s.isOn());
        assertNotNull(s.getControlPanel());
        if (s instanceof LightSwitch l) {
            assertNotNull(l.getType());
            if (s instanceof LightSwitchArea a) {
                assertNotNull(a.getSide());
            }
        }
    }

    public static void buildJoystick(AbstractJoystick joystick) {
        if (joystick instanceof Joystick j) {
            assertNotNull(j.getButtonLeft());
            buildJoystickButton(j.getButtonLeft(), Position.LEFT);
            assertNotNull(j.getButtonRight());
            buildJoystickButton(j.getButtonRight(), Position.RIGHT);
            assertNotNull(j.getTurret());
            assertNotNull(j.getTaster());
            assertNotNull(j.getTaster().getJoystick());
            assertNotNull(j.getSection());
        }
    }

    public static void buildJoystickButton(ButtonJoy b, Position p) {
        assertNotNull(b.getJoystick());
        assertNotNull(b.getPosition());
        assertEquals(p, b.getPosition());
    }

    public static void buildRotaryKnob(RotaryKnob r) {
        int stdStage = 1;
        assertNotNull(r.getTurretSection());
        assertNotNull(r.getTurret());
        assertEquals(stdStage, r.getStage());

    }

    public static void buildDoor(BusDoor d) {
        assertNotNull(d.getButtonDoor());
        assertNotNull(d.getCabin());
        assertNotNull(d.getSide());
        assertNotNull(d.getCardReader());
    }

    @Test
    @Order(2)
    public void usageControlPanel() {
        int amountSwitches = 5;
        boolean[] switches;
        switches = new boolean[amountSwitches];

        for (int i = 0; i < amountSwitches; i++) {
            switches[i] = true;
        }
        lightSetup(switches[0], switches[1], switches[2], switches[3], switches[4]);

        lightState(switches[0], switches[1], switches[2], switches[3], switches[4]);

        lightSetup(switches[0], switches[1], switches[2], switches[3], switches[4]);

        for (int i = 0; i < amountSwitches; i++) {
            switches[i] = false;
        }

        lightState(switches[0], switches[1], switches[2], switches[3], switches[4]);

    }

    @Test
    @Order(3)
    public void handleParking() {//s01
        setupCabin(false, true, false);
        //01-03
        operationalReadiness(false, true, false);


        //04-05
        turretsDeactivated();

        lightSetup(false, false, false, false, false);
        //06-10
        lightState(false, false, false, false, false);

        //11-12
        fullTanks();

        //
        assertEquals(100, flf.getPowerUnit().getBatteryAmountInPercent());
        assertEquals(400000, flf.getPowerUnit().getBatteryAmount());

        //14
        rotaryKnobStage(1, flf.getCabin().getDriverSection().getRotaryKnob());

        //15
        rotaryKnobStage(1, flf.getCabin().getOperatorSection().getRotaryKnob());

    }

    @Test
    @Order(4)
    public void handleInspectionDrive() {//s02
        setupCabin(true, false, true);

        //01-03
        operationalReadiness(true, false, true);

        //04-05
        turretsDeactivated();

        lightSetup(false, false, true, true, false);
        //06-10
        lightState(false, false, true, true, false);

        //11-12
        fullTanks();

        //13
        rotaryKnobStage(1, flf.getCabin().getDriverSection().getRotaryKnob());

        //14
        int kmhChange = 4;
        int kmh = 0;
        int usagePerkmh = 25;
        int usage = 0;
        rotaryKnobStage(1, flf.getCabin().getOperatorSection().getRotaryKnob());
        //fahrt part 1
        for (int i = 0; i < 7; i++) {

            flf.getCabin().getDriverSection().getAccelerator().stepOn();
            flf.getDriveUnit().drive();
            kmh += kmhChange;
            usage += kmh * usagePerkmh;
        }
        driveIteration(5);
        usage += kmh * usagePerkmh * 5;
        flf.getCabin().getDriverSection().getSteeringWheel().steerLeft(5);
        driveIteration(3);
        usage += kmh * usagePerkmh * 3;
        //15
        assertEquals(-5, flf.getDriveUnit().getDirection());


        //Fahrt Part 2
        flf.getCabin().getDriverSection().getSteeringWheel().steerRight(5);
        driveIteration(5);
        usage += kmh * usagePerkmh * 5;
        //16
        assertEquals(0, flf.getDriveUnit().getDirection());


        //Fahrt Part 3
        flf.getCabin().getDriverSection().getSteeringWheel().steerRight(5);
        driveIteration(5);
        usage += kmh * usagePerkmh * 5;
        //17
        assertEquals(5, flf.getDriveUnit().getDirection());

        for (int i = 0; i < 7; i++) {
            flf.getCabin().getDriverSection().getBrake().stepOn();
            flf.getDriveUnit().drive();
            kmh -= kmhChange;
            usage += kmh * usagePerkmh;
        }
        int maxEnergies = ((DriveUnit) flf.getDriveUnit()).getBatteryManagement().maxAmount();
        assertEquals(maxEnergies - usage, flf.getPowerUnit().getBatteryAmount());
    }

    @Test
    @Order(5)
    public void handleEmergencyService() {//s03
        setupCabin(true, false, true);

        //01-03
        operationalReadiness(true, false, true);

        //04-05
        turretsDeactivated();

        lightSetup(true, false, true, true, true);
        //06-10
        lightState(true, false, true, true, true);

        //11-12
        fullTanks();

        //13
        rotaryKnobStage(1, flf.getCabin().getDriverSection().getRotaryKnob());

        //14
        rotaryKnobStage(1, flf.getCabin().getOperatorSection().getRotaryKnob());

        int kmhChange = 4;
        int kmh = 0;
        int usagePerkmh = 25;
        int usage = 0;
        rotaryKnobStage(1, flf.getCabin().getOperatorSection().getRotaryKnob());
        //fahrt
        for (int i = 0; i < 20; i++) {

            flf.getCabin().getDriverSection().getAccelerator().stepOn();
            flf.getDriveUnit().drive();
            kmh += kmhChange;
            usage += kmh * usagePerkmh;
        }
        driveIteration(10);
        usage += kmh * usagePerkmh * 10;
        //15
        int maxEnergies = ((DriveUnit) flf.getDriveUnit()).getBatteryManagement().maxAmount();
        assertEquals(maxEnergies - usage, flf.getPowerUnit().getBatteryAmount());
    }

    @Test
    @Order(6)
    public void handleFuelTruckOnFire() {//s04
        setupCabin(true, false, true);

        //01-03
        operationalReadiness(true, false, true);

        lightSetup(true, true, true, true, true);
        //04-08
        lightState(true, true, true, true, true);

        //09-10
        fullTanks();

        int[] tanks = flf.getMixingUnit().getTanksFillState();
        int water = tanks[0];
        int foam = tanks[1];

        flf.getCabin().getOperatorSection().getControlpanel().getSelfProtection().turnSwitch();
        flf.getCabin().getOperatorSection().getControlpanel().getSelfProtection().turnSwitch();

        water -= 700;

        //11
        usageWaterFoam(water, foam);
        DriverSection drs = flf.getCabin().getDriverSection();
        //12-13
        turretTest(drs.getJoystick(), 2, drs.getRotaryKnob(), 5, 3, 3000, 5, true);

        OperatorSection ops = flf.getCabin().getOperatorSection();
        //14-15
        turretTest(ops.getJoystick(), 1, ops.getRotaryKnob(), 2, 3, 2500, 3, true);
    }

    @Test
    @Order(7)
    public void handlePushbackVehicleOnFire() {//s05
        setupCabin(true, false, true);

        //01-03
        operationalReadiness(true, false, true);

        lightSetup(true, true, true, true, true);
        //04-08
        lightState(true, true, true, true, true);

        //09-10
        fullTanks();

        DriverSection drs = flf.getCabin().getDriverSection();
        //11-12
        turretTest(drs.getJoystick(), 3, drs.getRotaryKnob(), 6, 3, 3500, 10, true);

        OperatorSection ops = flf.getCabin().getOperatorSection();
        //13-14
        turretTest(ops.getJoystick(), 2, ops.getRotaryKnob(), 2, 5, 2500, 5, true);

        //15-16
        turretTest(drs.getJoystick(), 2, drs.getRotaryKnob(), -5, 3, 1000, 3, true);
    }

    @Test
    @Order(8)
    public void handleAirplaneEngineFire() {//s06
        setupCabin(true, false, true);

        //01-03
        operationalReadiness(true, false, true);

        lightSetup(true, true, true, true, true);
        //04-08
        lightState(true, true, true, true, true);

        //09-10
        fullTanks();
        DriverSection drs = flf.getCabin().getDriverSection();
        //11-12
        turretTest(drs.getJoystick(), 3, drs.getRotaryKnob(), 6, 5, 3500, 10, true);

        OperatorSection ops = flf.getCabin().getOperatorSection();
        //13-14
        turretTest(ops.getJoystick(), 3, ops.getRotaryKnob(), 2, 5, 2500, 10, true);

        int[] tanks = flf.getMixingUnit().getTanksFillState();
        int water = tanks[0];
        int foam = tanks[1];

        //15
        useTurret(ops.getJoystick(), 0, ops.getRotaryKnob(), 0, 5);
        water -= (5 * 2500 * 0.9);
        foam -= (5 * 2500 * 0.1);
        usageWaterFoam(water, foam);

        //16
        useTurret(ops.getJoystick(), 2, ops.getRotaryKnob(), 2, 5);
        water -= (5 * 1000 * 0.97);
        foam -= (5 * 1000 * 0.03);
        usageWaterFoam(water, foam);

    }

    private void setupCabin(boolean frontSeatsOccupied, boolean doorsOpen, boolean motorOn) {
        if (frontSeatsOccupied) {
            flf.getCabin().getDriverSection().getSeat().sit();
            flf.getCabin().getOperatorSection().getSeat().sit();
        }

        if (doorsOpen) {
            flf.getCabin().getBusDoorLeft().lockUnlock();
            flf.getCabin().getBusDoorLeft().getButtonDoor().press();
            flf.getCabin().getBusDoorRight().lockUnlock();
            flf.getCabin().getBusDoorRight().getButtonDoor().press();
        }

        if (motorOn) flf.getCabin().getOperatorSection().getControlpanel().getMotorSwitch().turnSwitch();
        flf.getPowerUnit().chargePower(400000);
        flf.getMixingUnit().fillTanks();
    }

    private void operationalReadiness(boolean frontSeatsOccupied, boolean doorsOpen, boolean motorOn) {
        assertEquals(frontSeatsOccupied, flf.getCabin().getDriverSection().getSeat().isOccupied());
        assertEquals(frontSeatsOccupied, flf.getCabin().getOperatorSection().getSeat().isOccupied());
        assertEquals(doorsOpen, flf.getCabin().getBusDoorLeft().isOpen());
        assertEquals(doorsOpen, flf.getCabin().getBusDoorRight().isOpen());
        assertEquals(motorOn, flf.getDriveUnit().getMotorState());
        assertEquals(motorOn, flf.getCabin().getOperatorSection().getControlpanel().getMotorSwitch().isOn());
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

    private void fullTanks() {
        int[] max = flf.getMixingUnit().getTanksCapacity();
        int[] current = flf.getMixingUnit().getTanksFillState();
        for (int i = 0; i < max.length; i++) {
            assertEquals(max[i], current[i]);
        }
    }

    private void turretsDeactivated() {
        Assertions.assertFalse(flf.getCabin().getOperatorSection().getJoystick().getTurret().isActive());
        Assertions.assertFalse(flf.getCabin().getDriverSection().getJoystick().getTurret().isActive());
    }

    private void rotaryKnobStage(int stage, RotaryKnob rotaryKnob) {
        assertEquals(stage, rotaryKnob.getStage());
    }

    private void driveIteration(int iteration) {
        for (int i = 0; i < iteration; i++)
            flf.getDriveUnit().drive();
    }

    private void usageWaterFoam(int expectedWater, int expectedFoam) {
        int[] tanks = flf.getMixingUnit().getTanksFillState();
        assertEquals(expectedWater, tanks[0]);
        assertEquals(expectedFoam, tanks[1]);
    }

    private void useTurret(AbstractJoystick joystick, int presses, RotaryKnob rotaryKnob, int turns, int iteration) {
        if (joystick instanceof Joystick joy) {
            if (!joystick.getTurret().isActive()) {
                joy.getButtonLeft().press();
            }
            for (int i = 0; i < presses; i++) {
                joy.getButtonRight().press();
            }
            for (int i = 0; i < Math.abs(turns); i++) {
                if (turns > 0) {
                    rotaryKnob.turnRight();
                } else {
                    rotaryKnob.turnLeft();
                }
            }
            for (int i = 0; i < iteration; i++) {
                joy.getTaster().press();
            }
        }

    }

    private void joystickState(AbstractJoystick joystick, int expectedOutput, int expectedRatio, boolean state) {
        assertEquals(expectedOutput, joystick.getTurret().getTurretOutput());
        assertEquals(expectedRatio, joystick.getTurret().getFoam());
        assertEquals(state, joystick.getTurret().isActive());
    }

    private void turretTest(AbstractJoystick joystick, int presses, RotaryKnob rotaryKnob, int turns, int iteration, int waterUsage, int foamRatio, boolean turretState) {
        int[] tanks = flf.getMixingUnit().getTanksFillState();
        int water = tanks[0];
        int foam = tanks[1];
        useTurret(joystick, presses, rotaryKnob, turns, iteration);
        water -= (iteration * waterUsage * ((double) (100 - foamRatio) / 100));
        foam -= (iteration * waterUsage * ((double) foamRatio / 100));
        joystickState(joystick, waterUsage, foamRatio, turretState);
        usageWaterFoam(water, foam);
    }
}