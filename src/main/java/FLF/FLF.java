package FLF;

import cabin.BusDoor;
import cabin.Cabin;
import cabin.driverSection.DriverSection;
import cabin.operatorSection.Controlpanel;
import cabin.operatorSection.OperatorSection;
import ccu.CCU;
import complex1.DES;
import complex1.IDCard;
import complex1.Person;
import complex1.RFIDChip;
import complex2.IntelligentJoystick;
import driveUnit.DriveUnit;
import driveUnit.ElectricEngine;
import driveUnit.IDriveUnit;
import driveUnit.IPowerUnit;
import driveUnit.electrical.Battery;
import driveUnit.electrical.BatteryBox;
import driveUnit.electrical.BatteryManagement;
import driveUnit.mechanical.Axis;
import driveUnit.mechanical.BrakeDisc;
import driveUnit.mechanical.SteeringAxis;
import driveUnit.mechanical.Tire;
import inputs.buttons.ButtonDoor;
import inputs.switches.LightSwitch;
import inputs.switches.LightSwitchArea;
import inputs.switches.MotorSwitch;
import inputs.switches.SelfProtection;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.Joystick;
import inputs.turretInputs.RotaryKnob;
import lights.*;
import mixingUnit.IMixingUnit;
import mixingUnit.MixingUnit;
import teil2.task04.AES;
import teil2.task04.CentralConfiguration;
import teil2.task04.RSA;
import teil2.task05.Pin;
import teil2.task05.ThreeToOneAdapter;
import teil2.task08.TankLed;
import teil2.task08.TankSensor;
import teil2.task09.Tester;
import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;
import turrets.turretsWithFoam.Segment;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FLF {

    private Cabin cabin;
    private IDriveUnit driveUnit;
    private IPowerUnit powerUnit;
    private Light[] lights;
    private IMixingUnit mixingUnit;
    private String name;
    private Person[] users;
    private DriverSection driverSection = new DriverSection();
    private CCU centralUnit = new CCU(driverSection);
    private BatteryBox box;

    public FLF(Builder builder) {

        cabin = builder.cabin;
        driveUnit = builder.driveUnit;
        powerUnit = (IPowerUnit) builder.driveUnit;
        lights = builder.lights;
        mixingUnit = builder.mixingUnit;
        name = builder.name;
        users = builder.users;

    }

    public IPowerUnit getPowerUnit() {
        return powerUnit;
    }

    public IMixingUnit getMixingUnit() {
        return mixingUnit;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public IDriveUnit getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(IDriveUnit driveUnit) {
        this.driveUnit = driveUnit;
    }

    public Light[] getLights() {
        return lights;
    }

    public void setLights(Light[] lights) {
        this.lights = lights;
    }

    public CCU getCCU() {
        return centralUnit;
    }
    public BatteryBox getBatteries() {
        return box;
    }

    public static class Builder {

        private Cabin cabin;
        private IDriveUnit driveUnit;
        private final Light[] lights;
        private IMixingUnit mixingUnit;
        private CCU ccu;
        private String name;
        private Person[] users;

        public Builder(boolean intelligentJoystick) {

            name = "DUS | FLF.FLF-5";

            driveUnit = buildDriveUnit();

            mixingUnit = new MixingUnit();

            DriverSection driverSection = new DriverSection();
            ccu = new CCU(driverSection);

            try {
                switch (CentralConfiguration.instance.encryptionStrategy) {
                    case "DES":
                        ccu.setEncryptionStrategy(new DES());
                        break;
                    case "AES":
                        ccu.setEncryptionStrategy(new AES());
                        break;
                    case "RSA":
                        ccu.setEncryptionStrategy(new RSA());
                        break;
                }
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
            ccu.setCode(6072);

            RFIDChip rfid1 = null;

            try {
                rfid1 = new RFIDChip(ccu.getEncryptionStrategy().encrypt("FT-DUS-FLF.FLF-5-Red Adair-6072"));
            } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }
            IDCard idPerson1 = new IDCard(rfid1);

            RFIDChip rfid2 = null;

            try {
                rfid2 = new RFIDChip(ccu.getEncryptionStrategy().encrypt("FT-DUS-FLF.FLF-5-Sam-6072"));
            } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }
            IDCard idPerson2 = new IDCard(rfid2);

            users = new Person[2];
            users[0] = new Person(idPerson1, "Red Adair");
            users[1] = new Person(idPerson2, "Sam");

            ccu.setUsers(users);

            ccu.setDriveUnit(driveUnit);
            ccu.setMixingUnit(mixingUnit);

            FloorSprayNozzle[] floorSprayNozzles = new FloorSprayNozzle[7];
            for (int i = 0; i < 7; i++) {
                floorSprayNozzles[i] = new FloorSprayNozzle(ccu);
            }

            ccu.setFloorSprayNozzle(floorSprayNozzles);

            ccu.setLights(combineLightArrays());
            lights = ccu.getLights();

            Controlpanel controlpanel = new Controlpanel();
            controlpanel.setLightSwitches(buildSwitch(controlpanel));
            controlpanel.setMotorSwitch(new MotorSwitch(controlpanel));
            controlpanel.setSelfProtection(new SelfProtection(controlpanel));
            OperatorSection operatorSection = new OperatorSection(controlpanel, ccu);

            ArrayList<Segment> segments = new ArrayList<>();

            segments.add(new Segment(6));
            segments.add(new Segment(6));
            segments.add(new Segment(5));


            RoofTurret roofTurret = new RoofTurret(segments);
            FrontTurret frontTurret = new FrontTurret();

            RotaryKnob operatorKnob = new RotaryKnob(operatorSection, roofTurret);
            RotaryKnob driverKnob = new RotaryKnob(driverSection, frontTurret);

            AbstractJoystick operatorJoystick;
            AbstractJoystick driverJoystick;

            if (intelligentJoystick) {
                operatorJoystick = new IntelligentJoystick(operatorSection, roofTurret);
                driverJoystick = new IntelligentJoystick(operatorSection, roofTurret);
            } else {
                operatorJoystick = new Joystick(operatorSection, roofTurret);
                driverJoystick = new Joystick(driverSection, frontTurret);
            }

            operatorSection.setJoystick(operatorJoystick);
            operatorSection.setRotaryKnob(operatorKnob);
            driverSection.setJoystick(driverJoystick);
            driverSection.setRotaryKnob(driverKnob);

            ccu.setOperatorSection(operatorSection);
            ccu.setDriverSection(driverSection);
            operatorSection.setCcu(ccu);
            driverSection.setCcu(ccu);
            ((DriveUnit) driveUnit).setCcu(ccu);
            controlpanel.setOperatorSection(operatorSection);

            cabin = new Cabin(driverSection, operatorSection);

            driverSection.setCabin(cabin);
            cabin.setDriverSection(driverSection);
            operatorSection.setCabin(cabin);
            cabin.setOperatorSection(operatorSection);

            BusDoor busDoorLeft = new BusDoor(cabin, Side.LEFT);
            busDoorLeft.setButtonDoor(new ButtonDoor(busDoorLeft));
            cabin.setBusDoorLeft(busDoorLeft);

            BusDoor busDoorRight = new BusDoor(cabin, Side.RIGHT);
            busDoorRight.setButtonDoor(new ButtonDoor(busDoorRight));
            cabin.setBusDoorRight(busDoorRight);


        }

        private LightSwitch[] buildSwitch(Controlpanel controlpanel) {

            LightSwitch[] lightSwitches = new LightSwitch[5];

            lightSwitches[0] = new LightSwitch(Type.WARNINGLIGHT, controlpanel);
            lightSwitches[1] = new LightSwitch(Type.BLUELIGHT, controlpanel);
            lightSwitches[2] = new LightSwitchArea(Type.SPOTLIGHT, controlpanel, Side.FRONT);
            lightSwitches[3] = new LightSwitchArea(Type.SPOTLIGHT, controlpanel, Side.ROOF);
            lightSwitches[4] = new LightSwitchArea(Type.SPOTLIGHT, controlpanel, Side.SIDE);

            return lightSwitches;
        }

        private LedLight[] buildLedLights() {

            LedLight[] ledLights = new LedLight[12];
            int offset = 0;
            int i;
            for (i = 0; i < 2; i++) {
                ledLights[i + offset] = new LedLight(1, ccu, null, LightColor.BLUE, Side.FRONT, Type.BLUELIGHT);
            }
            offset += i;

            for (i = 0; i < 2; i++) {
                ledLights[i + offset] = new LedLight(4, ccu, Position.TOPRIGHT, LightColor.BLUE, Side.ROOF, Type.BLUELIGHT);
            }

            offset += i;

            for (i = 0; i < 2; i++) {
                ledLights[i + offset] = new LedLight(4, ccu, Position.TOPLEFT, LightColor.BLUE, Side.FRONT, Type.BLUELIGHT);
            }

            offset += i;

            for (i = 0; i < 2; i++) {
                ledLights[i + offset] = new LedLight(2, ccu, Position.LEFT, LightColor.BLUE, Side.REAR, Type.BLUELIGHT);
            }

            offset += i;

            for (i = 0; i < 2; i++) {
                ledLights[i + offset] = new LedLight(2, ccu, Position.RIGHT, LightColor.BLUE, Side.REAR, Type.BLUELIGHT);
            }

            offset += i;
            ledLights[offset] = new LedLight(1, ccu, Position.LEFT, LightColor.ORANGE, Side.FRONT, Type.WARNINGLIGHT);
            offset++;
            ledLights[offset] = new LedLight(1, ccu, Position.RIGHT, LightColor.ORANGE, Side.REAR, Type.WARNINGLIGHT);

            return ledLights;

        }

        private Light[] buildLights() {
            Light[] lights = new Light[26];
            int offset = 0;
            int i;
            for (i = 0; i < 3; i++) {
                lights[i + offset] = new Light(ccu, Position.LEFT, LightColor.WHITE, Side.FRONT, Type.SPOTLIGHT);
            }
            offset += i;

            for (i = 0; i < 3; i++) {
                lights[i + offset] = new Light(ccu, Position.RIGHT, LightColor.WHITE, Side.FRONT, Type.SPOTLIGHT);
            }
            offset += i;

            for (i = 0; i < 4; i++) {
                lights[i + offset] = new Light(ccu, Position.TOP, LightColor.WHITE, Side.ROOF, Type.SPOTLIGHT);
            }
            offset += i;

            for (i = 0; i < 5; i++) {
                lights[i + offset] = new Light(ccu, Position.ANY, LightColor.WHITE, Side.LEFT, Type.SPOTLIGHT);
            }
            offset += i;

            for (i = 0; i < 5; i++) {
                lights[i + offset] = new Light(ccu, Position.ANY, LightColor.WHITE, Side.RIGHT, Type.SPOTLIGHT);
            }
            offset += i;

            lights[offset] = new Light(ccu, Position.LEFT, LightColor.WHITE, Side.REAR, Type.BRAKELIGHT);
            offset++;

            lights[offset] = new Light(ccu, Position.RIGHT, LightColor.WHITE, Side.REAR, Type.BRAKELIGHT);
            offset++;

            lights[offset] = new Light(ccu, Position.LEFT, LightColor.ORANGE, Side.LEFT, Type.DIRECTIONINDICATIONLIGHT);
            offset++;

            lights[offset] = new Light(ccu, Position.RIGHT, LightColor.ORANGE, Side.LEFT, Type.DIRECTIONINDICATIONLIGHT);
            offset++;

            lights[offset] = new Light(ccu, Position.LEFT, LightColor.ORANGE, Side.RIGHT, Type.DIRECTIONINDICATIONLIGHT);
            offset++;

            lights[offset] = new Light(ccu, Position.RIGHT, LightColor.ORANGE, Side.LEFT, Type.DIRECTIONINDICATIONLIGHT);

            return lights;
        }


        private Light[] combineLightArrays() {


            LedLight[] ledLights = buildLedLights();
            Light[] lights = buildLights();
            Light[] combinedLights = new Light[ledLights.length + lights.length];

            System.arraycopy(ledLights, 0, combinedLights, 0, ledLights.length);
            System.arraycopy(lights, 0, combinedLights, ledLights.length, lights.length);

            return combinedLights;
        }


        private DriveUnit buildDriveUnit() {


            Axis[] axis = new Axis[2];
            for (int i = 0; i < axis.length; i++) {
                Tire[] tires = new Tire[2];
                for (int j = 0; j < tires.length; j++) {
                    tires[j] = new Tire();
                }
                BrakeDisc[] brakeDiscs = new BrakeDisc[6];
                for (int j = 0; j < brakeDiscs.length; j++) {
                    brakeDiscs[j] = new BrakeDisc();
                }
                axis[i] = new Axis(tires, brakeDiscs);
            }


            SteeringAxis[] steeringAxes = new SteeringAxis[2];
            for (int i = 0; i < axis.length; i++) {
                Tire[] tires = new Tire[2];
                for (int j = 0; j < tires.length; j++) {
                    tires[j] = new Tire();
                }
                BrakeDisc[] brakeDiscs = new BrakeDisc[6];
                for (int j = 0; j < brakeDiscs.length; j++) {
                    brakeDiscs[j] = new BrakeDisc();
                }
                steeringAxes[i] = new SteeringAxis(tires, brakeDiscs, 0);
            }


            Battery[][] batteries = new Battery[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {

                    batteries[i][j] = new Battery();

                }
            }

            BatteryBox batteryBox = new BatteryBox(batteries);

            BatteryManagement.INSTANCE.setBatteryBox(batteryBox);

            DriveUnit driveUnit = new DriveUnit(0, axis, steeringAxes, BatteryManagement.INSTANCE);

            ElectricEngine[] electricEngines = new ElectricEngine[2];
            for (int i = 0; i < 2; i++) {
                electricEngines[i] = new ElectricEngine();
                electricEngines[i].setPowerUnit(driveUnit);
            }

            driveUnit.setEngines(electricEngines);

            return driveUnit;

        }

        public FLF build() {
            return new FLF(this);
        }

        public FLF buildTart2() {
            FLF flf = new FLF(this);
            flf = extension(flf);
            return flf;
        }

        public FLF extension(FLF flf) {
            //01

            //03
            BatteryManagement management = ((DriveUnit) flf.getDriveUnit()).getBatteryManagement();
            management.setBatteryBox(new BatteryBox.Builder()
                    .boxWidth(2)
                    .boxLength(2)
                    .batteryWidth(100)
                    .batteryLength(100)
                    .batteryHeight(10)
                    .buildCellBox());
            //04 bereits weiter oben in Consruktor
            //05
            ThreeToOneAdapter threeToOneAdapter = new ThreeToOneAdapter(new Pin(), new Pin(), new Pin(), management);
            management.setThreePinConnector(threeToOneAdapter);
            //08
            TankLed tempLight = new TankLed(new LedLight(2, LightColor.WHITE, Type.SENSORLIGHT));
            TankSensor tempSensor = new TankSensor();
            tempSensor.addListener(tempLight);
            ((MixingUnit) flf.getMixingUnit()).getWaterTank().setSensor(tempSensor);
            flf.getCabin().getOperatorSection().getControlpanel().setWaterLed(tempLight);
            tempLight = new TankLed(new LedLight(2, LightColor.WHITE, Type.SENSORLIGHT));
            tempSensor = new TankSensor();
            tempSensor.addListener(tempLight);
            ((MixingUnit) flf.getMixingUnit()).getFoamTank().setSensor(tempSensor);
            flf.getCabin().getOperatorSection().getControlpanel().setFoamLed(tempLight);
            FrontTurret front = (FrontTurret) flf.getCabin().getDriverSection().getJoystick().getTurret();
            ;
            RoofTurret roof = (RoofTurret) flf.getCabin().getOperatorSection().getJoystick().getTurret();
            //09
            CCU ccu = flf.getCabin().getDriverSection().getCcu();
            ccu.addUnitToTest(front);
            ccu.addUnitToTest(roof);
            for (FloorSprayNozzle nuzzle : ccu.getFloorSprayNozzle()) {
                ccu.addUnitToTest(nuzzle);
            }
            ccu.setTester(new Tester(ccu, ccu.getFloorSprayNozzle().length));
            return flf;
        }

    }
}