package ccu;

import cabin.driverSection.BatteryDisplay;
import cabin.driverSection.DriverSection;
import cabin.driverSection.IDisplay;
import cabin.driverSection.SpeedDisplay;
import cabin.operatorSection.OperatorSection;
import com.google.common.eventbus.EventBus;
import complex1.Person;
import driveUnit.IDriveUnit;
import inputs.buttons.ButtonDoor;
import inputs.buttons.IButton;
import inputs.driverInputs.Accelerator;
import inputs.driverInputs.Brake;
import inputs.driverInputs.IPedal;
import inputs.switches.*;
import inputs.turretInputs.ButtonJoy;
import inputs.turretInputs.Taster;
import lights.Light;
import lights.Side;
import lights.Type;
import mixingUnit.IMixingUnit;
import teil2.task02.*;
import teil2.task04.IEncryptionStrategy;
import teil2.task06.SwitchType;
import teil2.task09.ITesterVisitor;
import teil2.task09.IUnitToTest;
import teil2.task09.Tester;
import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;
import turrets.turretsWithFoam.TurretWithFoam;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CCU implements ITurretControl, IDriveUnitControl, ILightControl, ITesterVisitor {

    private final EventBus eventBus;
    private Light[] lights;
    private HashMap<SwitchType, Light[]> light;
    private IMixingUnit mixingUnit;
    private IDriveUnit driveUnit;
    private DriverSection driverSection;
    private OperatorSection operatorSection;
    private FloorSprayNozzle[] floorSprayNozzle;
    private IEncryptionStrategy encryptionStrategy;
    private int code;
    private Person[] users;
    private List<IUnitToTest> unitsToTest;
    private Tester tester;

    public CCU(DriverSection driverSection) {
        this.driverSection = driverSection;
        eventBus = new EventBus("event");
        floorSprayNozzle = new FloorSprayNozzle[7];
        for (int i = 0; i < 7; i++) {
            floorSprayNozzle[i] = new FloorSprayNozzle(this);
            addSubscriber(floorSprayNozzle[i]);
        }
        unitsToTest = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void setUsers(Person[] users) {
        this.users = users;
    }

    public IEncryptionStrategy getEncryptionStrategy() {
        return encryptionStrategy;
    }

    public void setEncryptionStrategy(IEncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public IMixingUnit getMixingUnit() {
        return mixingUnit;
    }

    public void setMixingUnit(IMixingUnit mixingUnit) {
        this.mixingUnit = mixingUnit;
    }

    public FloorSprayNozzle[] getFloorSprayNozzle() {
        return floorSprayNozzle;
    }

    public void setFloorSprayNozzle(FloorSprayNozzle[] floorSprayNozzle) {
        this.floorSprayNozzle = floorSprayNozzle;
    }

    public IDriveUnit getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(IDriveUnit driveUnit) {
        this.driveUnit = driveUnit;
    }

    public DriverSection getDriverSection() {
        return driverSection;
    }

    public void setDriverSection(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public OperatorSection getOperatorSection() {
        return operatorSection;
    }

    public void setOperatorSection(OperatorSection operatorSection) {
        this.operatorSection = operatorSection;
    }

    public Light[] getLights() {
        return lights;
    }

    public void setLights(Light[] lights) {
        this.lights = lights;
    }

    public void turnSwitch(Switch s) {

        if (s instanceof LightSwitch sw) {
            Type type = sw.getType();
            if (type == Type.BLUELIGHT) {
                eventBus.post(new BlueLightsEvent());
            } else if (type == Type.WARNINGLIGHT) {
                eventBus.post(new WarningLightEvent());
            }
        }

        if (s instanceof LightSwitchArea sa) {
            Type type = ((LightSwitchArea) s).getType();
            Side side = ((LightSwitchArea) s).getSide();


            if (sa.getType() == Type.SPOTLIGHT) {
                if (sa.getSide() == Side.FRONT) {
                    eventBus.post(new FrontLightsEvent());
                } else if (side == Side.ROOF) {
                    eventBus.post(new RoofLightsEvent());
                } else if (side == Side.SIDE) {
                    if (sa.getSide() == Side.LEFT || sa.getSide() == Side.RIGHT || sa.getSide() == Side.SIDE) {
                        eventBus.post(new SideLightsEvent());
                    }
                }
            }
        } else if (s instanceof MotorSwitch) {
            if (driveUnit.getMotorState()) {
                driveUnit.shutdownEngine();
            } else {
                driveUnit.startEngine();
                if (!unitsToTest.isEmpty() && tester != null) {
                    for (IUnitToTest unit : unitsToTest) {
                        unit.accept(this);
                    }
                }
            }
        } else if (s instanceof SelfProtection) {
            for (FloorSprayNozzle f : floorSprayNozzle) {
                f.onOff();
                if (f.isActive()) {
                    f.spray(mixingUnit.takeOut(f));
                }
            }
        }
    }


    public void turnOnOffTurret(TurretWithFoam turret) {
        turret.onOff();
    }

    public void increaseOutput(TurretWithFoam turret) {
        turret.increaseWater();
    }

    public void decreaseOutput(TurretWithFoam turret) {
        turret.decreaseWater();
    }

    public void increaseFoam(TurretWithFoam turret) {
        turret.increaseFoam();
    }

    public void buttonPress(IButton button) {
        if (button instanceof ButtonDoor) {
            ((ButtonDoor) button).getBusDoor().openClose();
        }
    }

    @Override
    public void buttonPress(IButton button, TurretWithFoam turret) {
        if (button instanceof ButtonJoy bj) {
            switch (bj.getPosition()) {
                case LEFT -> turnOnOffTurret(turret);
                case RIGHT -> {
                    if (bj.getJoystick().getTurret().isActive()) increaseFoam(turret);
                }
                default -> {
                }
            }
        } else if (button instanceof Taster) {
            turret.spray(mixingUnit.mixing(turret));
        }
    }

    public void turnLeft(TurretWithFoam turret) {
        decreaseOutput(turret);
    }

    public void turnRight(TurretWithFoam turret) {
        increaseOutput(turret);
    }

    public void stepOn(IPedal pedal) {
        if (pedal instanceof Brake) {
            driveUnit.changeSpeed(-4);
        } else if (pedal instanceof Accelerator) {
            driveUnit.changeSpeed(4);
        }
    }

    @Override
    public void steer(int degree) {
        driveUnit.turn(degree);
    }

    public String show(IDisplay display) {
        if (display instanceof SpeedDisplay) {
            int speed = driveUnit.getSpeed();
            return "Speed: " + speed;
        } else if (display instanceof BatteryDisplay) {
            int speed = driveUnit.getSpeed();
            return "Speed: " + speed;
        }
        return "Not Specified";

    }

    @Override
    public void setLight(boolean onOff, ArrayList<Light> lights) {

        for (Light l : lights) {
            l.setOn(onOff);
        }

    }

    public void validateString(String s) {

        String dec = "";
        try {
            dec = encryptionStrategy.decrypt(s);
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        String cmpString = "FT-DUS-FLF.FLF-5-";
        String endString = "-" + getCode();

        for (Person u : users) {
            if (dec.equals(cmpString + u.getName() + endString)) {

                operatorSection.lockUnlock();

            }
        }

    }

    public void addUnitToTest(IUnitToTest testUnit) {
        unitsToTest.add(testUnit);
    }

    public void removeUnitToTest(IUnitToTest testUnit) {
        unitsToTest.remove(testUnit);
    }

    @Override
    public void visit(FloorSprayNozzle floorSprayNozzle) {
        tester.nozzleTest(floorSprayNozzle);
    }

    @Override
    public void visit(FrontTurret frontTurret) {
        tester.frontTurretTest(frontTurret);
    }

    @Override
    public void visit(RoofTurret roofTurret) {
        tester.roofTurretTest(roofTurret);
    }

    public List<IUnitToTest> getUnitsToTest() {
        return unitsToTest;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public void changeLightState(SwitchType switchType) {
        switch (switchType) {
            case headLightsFront -> eventBus.post(new FrontLightsEvent());
            case BlueLights -> eventBus.post(new BlueLightsEvent());
            case headLightsRoof -> eventBus.post(new RoofLightsEvent());
            case SideLights -> eventBus.post(new SideLightsEvent());
            case warningLights -> eventBus.post(new WarningLightEvent());
            default -> throw new RuntimeException();
        }
    }

    public void changeMotorState() {
        eventBus.post(new ElectricMotorEvent());
    }

    public void changeFloorSpraysNozzleState() {
        eventBus.post(new SelfProtectionEvent());
    }

}
