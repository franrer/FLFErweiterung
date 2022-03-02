package driveUnit;

import ccu.CCU;
import driveUnit.electrical.BatteryManagement;
import driveUnit.mechanical.Axis;
import driveUnit.mechanical.SteeringAxis;

public class DriveUnit implements IDriveUnit, IPowerUnit {
    private int speed;
    private Axis[] axis;
    private SteeringAxis[] steeringAxes;
    private ElectricEngine[] engines;
    private BatteryManagement batteryManagement;
    private CCU ccu;

    public DriveUnit(int speed, Axis[] axis, SteeringAxis[] steeringAxes, BatteryManagement batteryManagement) {
        this.speed = speed;
        this.axis = axis;
        this.steeringAxes = steeringAxes;
        this.engines = null;
        this.batteryManagement = batteryManagement;
    }

    public CCU getCcu() {
        return ccu;
    }

    public void setCcu(CCU ccu) {
        this.ccu = ccu;
    }

    @Override
    public void changeSpeed(int amount) {
        if (amount < 0) {
            for (Axis a : axis) {
                a.brake();
            }
            for (SteeringAxis s : steeringAxes) {
                s.brake();
            }
        }
        speed += amount;
    }

    @Override
    public void drive() {
        for (ElectricEngine e : engines)
            e.rotate(speed);
    }

    @Override
    public void turn(int degree) {
        for (SteeringAxis s : steeringAxes) {
            s.turn(degree);
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getDirection() {
        return steeringAxes[0].getDirection();
    }

    @Override
    public int getBatteryAmount() {
        return batteryManagement.lookAmount();
    }

    public Axis[] getAxis() {
        return axis;
    }

    public SteeringAxis[] getSteeringAxes() {
        return steeringAxes;
    }

    public ElectricEngine[] getEngines() {
        return engines;
    }

    public void setEngines(ElectricEngine[] engines) {
        this.engines = engines;
    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    @Override
    public int getBatteryAmountInPercent() {
        return batteryManagement.lookAmountInPercent();
    }

    @Override
    public String usePower(int amount) {
        return batteryManagement.use(amount);
    }

    @Override
    public String chargePower(int amount) {
        return batteryManagement.load(amount);
    }

    @Override
    public boolean getMotorState() {
        return engines[0].isStarted() && engines[1].isStarted();
    }

    @Override
    public void startEngine() {
        for (ElectricEngine e : engines) {
            e.start();
        }
    }

    @Override
    public void shutdownEngine() {
        for (ElectricEngine e : engines) {
            e.shutdown();
        }

    }


}
