package teil2.task09;

import cabin.driverSection.DriverSection;
import cabin.operatorSection.OperatorSection;
import ccu.CCU;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.Joystick;
import inputs.turretInputs.RotaryKnob;
import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;
import turrets.turretsWithFoam.TurretWithFoam;

public class Tester {
    private boolean frontTurretTestResult;
    private boolean roofTurretTestResult;
    private boolean[] nozzleTestResult;
    private CCU ccu;
    private int nozzleId;

    public Tester(CCU ccu, int nozzleAmount) {
        this.ccu = ccu;
        nozzleTestResult = new boolean[nozzleAmount];
        nozzleId = 0;
    }

    public void frontTurretTest(FrontTurret turret) {
        DriverSection drs = ccu.getDriverSection();
        frontTurretTestResult = turretTest(drs.getJoystick(), 0, drs.getRotaryKnob(), 0, 1, turret.getTurretOutput(), turret.getFoam(), turret.isActive());
    }

    public void roofTurretTest(RoofTurret turret) {
        OperatorSection ops = ccu.getOperatorSection();
        roofTurretTestResult = turretTest(ops.getJoystick(), 0, ops.getRotaryKnob(), 0, 1, turret.getTurretOutput(), turret.getFoam(), turret.isActive());
    }

    public void nozzleTest(FloorSprayNozzle nozzle) {
        nozzleTestResult[nozzleId] = innerNozzleTest(nozzle);
        nozzleId = ++nozzleId % nozzleTestResult.length;
    }

    public boolean getFrontTurretTestResult() {
        return frontTurretTestResult;
    }

    public boolean getRoofTurretTestResult() {
        return roofTurretTestResult;
    }

    public boolean[] getNozzleTestResult() {
        return nozzleTestResult;
    }

    private boolean innerNozzleTest(FloorSprayNozzle nozzle) {
        int waterBefore = ccu.getMixingUnit().getTanksFillState()[0];
        nozzle.spray(ccu.getMixingUnit().takeOut(nozzle));
        int waterAfter = ccu.getMixingUnit().getTanksFillState()[0];
        boolean result = true;
        if (result) result = (waterBefore - nozzle.getTurretOutput()) == waterAfter;
        return result;
    }

    private boolean usageWaterFoam(int expectedWater, int expectedFoam) {
        int[] tanks = ccu.getMixingUnit().getTanksFillState();
        boolean result = true;
        if (result) result = expectedWater == tanks[0];
        if (result) result = expectedFoam == tanks[1];
        return result;
    }

    private void useTurret(AbstractJoystick joystick, int presses, RotaryKnob rotaryKnob, int turns, int iteration) {
        boolean active=joystick.getTurret().isActive();
        if (joystick instanceof Joystick joy) {
            if (!active) {
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
        if(!active){
            ((Joystick)joystick).getButtonLeft().press();
        }

    }

    private boolean joystickState(AbstractJoystick joystick, int expectedOutput, int expectedRatio, boolean state) {
        boolean result = true;
        if (result) result = expectedOutput == joystick.getTurret().getTurretOutput();
        if (result) result = expectedRatio == joystick.getTurret().getFoam();
        if (result) result = state == joystick.getTurret().isActive();
        return result;
    }

    private boolean turretTest(AbstractJoystick joystick, int presses, RotaryKnob rotaryKnob, int turns, int iteration, int waterUsage, int foamRatio, boolean turretState) {
        int[] tanks = ccu.getMixingUnit().getTanksFillState();
        int water = tanks[0];
        int foam = tanks[1];
        useTurret(joystick, presses, rotaryKnob, turns, iteration);
        water -= (iteration * waterUsage * ((double) (100 - foamRatio) / 100));
        foam -= (iteration * waterUsage * ((double) foamRatio / 100));
        boolean result = true;
        if (result) result = joystickState(joystick, waterUsage, foamRatio, turretState);
        if (result) result = usageWaterFoam(water, foam);
        return result;
    }
}
