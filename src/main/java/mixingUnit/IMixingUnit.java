package mixingUnit;

import turrets.Turret;
import turrets.turretsWithFoam.TurretWithFoam;

public interface IMixingUnit {
    int takeOut(Turret turret);

    int mixing(TurretWithFoam turret);

    int[] getTanksCapacity();

    int[] getTanksFillState();

    void fillTanks();
}
