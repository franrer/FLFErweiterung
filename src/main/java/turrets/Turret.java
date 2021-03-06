package turrets;

import teil2.task02.Subscriber;

public abstract class Turret extends Subscriber {

    protected boolean isActive;
    protected int turretWater;


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
