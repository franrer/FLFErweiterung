package turrets;

public abstract class Turret {

    protected static Turret active;
    protected static Turret inactive;
    protected boolean isActive;
    protected int turretWater;
    private int value;

    public Turret(int turretWater) {
        this.turretWater = turretWater;
        isActive = false;
    }

    public void spray(int amount) {
        //spray
    }

    public void onOff() {
        isActive = (!isActive);
    }

    public int getTurretOutput() {
        return turretWater;
    }

    public boolean isActive() {
        return isActive;
    }
}
