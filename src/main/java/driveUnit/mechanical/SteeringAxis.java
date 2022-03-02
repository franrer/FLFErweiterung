package driveUnit.mechanical;

public class SteeringAxis extends Axis {
    private int direction;

    public SteeringAxis(Tire[] tires, BrakeDisc[] brakeDisc, int direction) {
        super(tires, brakeDisc);
        this.direction = 0;
    }

    public int getDirection() {
        return direction;
    }

    public void turn(int degree) {
        direction = direction + degree;
    }
}
