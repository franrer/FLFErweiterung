package turrets.turretsWithFoam;

import turrets.Turret;

public abstract class TurretWithFoam extends Turret {
    private int foam;

    public TurretWithFoam(int turretWater, int foam) {
        super(turretWater);
        this.foam = foam;
    }

    public TurretWithFoam(int turretWater) {
        super(turretWater);
        this.foam = 0;
    }

    public int getFoam() {
        return foam;
    }

    public void setFoam(int amount) {
        foam = amount;
    }

    public void increaseFoam() {
        switch (foam) {
            case 0 -> foam = 3;
            case 3 -> foam = 5;
            case 5 -> foam = 10;
            case 10 -> foam = 0;
        }
    }

    public abstract void pumpOut();

    public abstract void setCannonState(Turret cannonState);

    public abstract void increaseWater();

    public abstract void decreaseWater();

    public abstract int getStage();

}
