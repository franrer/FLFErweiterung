package ccu;

import inputs.buttons.IButton;
import turrets.turretsWithFoam.TurretWithFoam;

public interface ITurretControl {
    void buttonPress(IButton button, TurretWithFoam turret);

    void turnLeft(TurretWithFoam turret);

    void turnRight(TurretWithFoam turret);

    void turnOnOffTurret(TurretWithFoam turret);

    void increaseOutput(TurretWithFoam turret);

    void decreaseOutput(TurretWithFoam turret);

    void increaseFoam(TurretWithFoam turret);

}
