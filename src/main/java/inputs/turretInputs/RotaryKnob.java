package inputs.turretInputs;

import turrets.AbstractTurretSection;
import turrets.turretsWithFoam.TurretWithFoam;

public class RotaryKnob {


    private AbstractTurretSection turretSection;
    private TurretWithFoam turret;

    public RotaryKnob(AbstractTurretSection turretSection, TurretWithFoam turret) {
        this.turretSection = turretSection;
        this.turret = turret;
    }

    public void turnLeft() {
        turretSection.turnLeft(turret);

    }

    public void turnRight() {
        turretSection.turnRight(turret);
    }

    public AbstractTurretSection getTurretSection() {
        return turretSection;
    }

    public TurretWithFoam getTurret() {
        return turret;
    }

    public int getStage() {
        return turret.getStage();
    }

}
