package turrets.turretsWithFoam;

import teil2.task01.*;
import teil2.task09.ITesterVisitor;
import teil2.task09.IUnitToTest;
import turrets.Turret;

public class FrontTurret extends TurretWithFoam implements IUnitToTest {
    private Turret steps;
    private int position;
    private int value;
    private Turret cannonState;

    public FrontTurret() {
        super(500);
        this.position = 0;
    }
    private float tiltDegree;
    private Communicator mixDevice;
    public FrontTurret(Communicator mixer) {
        this();
        this.tiltDegree = 0;
        this.turretWater = getStage();
        this.mixDevice = mixer;
    }

    @Override
    public void pumpOut() {
        this.mixDevice.defill(steps.getValue());
    }
    public Communicator getMixDevice() {
        return mixDevice;
    }
    @Override
    public void setCannonState(Turret cannonState) {
        if (cannonState==Turret.active) {
            this.tiltDegree = 90;
        } else {
            this.tiltDegree =0;
        }
        this.cannonState = cannonState;
    }


    public void setSteps(Turret steps) {
        this.steps = steps;
    }

    public Turret getSteps() {
        return steps;
    }

    public float getTiltDegree() {
        return tiltDegree;
    }


    @Override
    public void increaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 1000;
            case 1000 -> turretWater = 1500;
            case 1500 -> turretWater = 2000;
            case 2000 -> turretWater = 2500;
            case 2500 -> turretWater = 3000;
            case 3000 -> turretWater = 3500;
            case 3500 -> turretWater = 500;

        }
    }

    @Override
    public void decreaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 3500;
            case 1000 -> turretWater = 500;
            case 1500 -> turretWater = 1000;
            case 2000 -> turretWater = 1500;
            case 2500 -> turretWater = 2000;
            case 3000 -> turretWater = 2500;
            case 3500 -> turretWater = 3000;
        }
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int getStage() {
        return switch (turretWater) {
            case 500 -> 1;
            case 1000 -> 2;
            case 1500 -> 3;
            case 2000 -> 4;
            case 2500 -> 5;
            case 3000 -> 6;
            case 3500 -> 7;
            default -> 0;
        };
    }


    @Override
    public void onOff() {
        if (isActive) {
            position = 90;
        } else {
            position = 0;
        }
        super.onOff();
    }

    @Override
    public void accept(ITesterVisitor visitor) {
        visitor.visit(this);
    }
}
