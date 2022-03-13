package turrets.turretsWithFoam;

import teil2.task09.ITesterVisitor;
import teil2.task09.IUnitToTest;
import turrets.Turret;

import java.util.ArrayList;

public class RoofTurret extends TurretWithFoam implements IUnitToTest {
    private int positionVertical;
    private ArrayList<Segment> segments;

    public RoofTurret(ArrayList<Segment> segments) {
        super(500);
        this.segments = segments;
        this.positionVertical = 0;
    }

    @Override
    public void increaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 1000;
            case 1000 -> turretWater = 2500;
            case 2500 -> turretWater = 500;
        }
    }

    @Override
    public int getStage() {
        return switch (turretWater) {
            case 500 -> 1;
            case 1000 -> 2;
            case 2500 -> 3;
            default -> 0;
        };
    }


    public int getPositionVertical() {
        return positionVertical;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    @Override
    public void decreaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 2500;
            case 1000 -> turretWater = 500;
            case 2500 -> turretWater = 1000;
        }
    }

    @Override
    public void onOff() {
        if (isActive) {
            positionVertical = 90;
        } else {
            positionVertical = 0;
        }
        super.onOff();
    }

    @Override
    public void accept(ITesterVisitor visitor) {
        visitor.visit(this);
    }
}
