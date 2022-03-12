package ccu;

import cabin.driverSection.BatteryDisplay;
import cabin.driverSection.DriverSection;
import cabin.driverSection.IDisplay;
import cabin.driverSection.SpeedDisplay;
import cabin.operatorSection.OperatorSection;
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
import teil2.task04.IEncryptionStrategy;
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
import java.util.List;


public class CCU implements ITurretControl, IDriveUnitControl, ILightControl, ITesterVisitor {

    private IMixingUnit mixingUnit;
    private IDriveUnit driveUnit;
    private DriverSection driverSection;
    private OperatorSection operatorSection;
    private Light[] lights;
    private FloorSprayNozzle[] floorSprayNozzle;
    private IEncryptionStrategy encryptionStrategy;
    private int code;
    private Person[] users;
    private List<IUnitToTest> unitsToTest;
    private Tester tester;

    public CCU(IMixingUnit mixingUnit, IDriveUnit driveUnit, DriverSection driverSection, OperatorSection operatorSection, Light[] lights) {
        this.mixingUnit = mixingUnit;
        this.driveUnit = driveUnit;
        this.driverSection = driverSection;
        this.operatorSection = operatorSection;
        this.lights = lights;
        floorSprayNozzle = new FloorSprayNozzle[7];
        for (int i = 0; i < 7; i++) {
            floorSprayNozzle[i] = new FloorSprayNozzle(this);
        }
        unitsToTest = new ArrayList<>();
    }

    public CCU(DriverSection driverSection) {
        unitsToTest = new ArrayList<>();
        this.driverSection = driverSection;
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

        if (s instanceof LightSwitch) {
            Type type = ((LightSwitch) s).getType();

            for (Light l : lights) {
                if (l.getType() == type && type != Type.SPOTLIGHT) {

                    l.onOff();
                }
            }
        }

        if (s instanceof LightSwitchArea) {
            Type type = ((LightSwitchArea) s).getType();
            Side side = ((LightSwitchArea) s).getSide();

            for (Light l : lights) {
                if (l.getType() == type) {
                    if (l.getSide() == side)
                        l.onOff();
                    else if (side == Side.SIDE)
                        if (l.getSide() == Side.LEFT || l.getSide() == Side.RIGHT || l.getSide() == Side.SIDE)
                            l.onOff();
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

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public Tester getTester() {
        return tester;
    }
}
