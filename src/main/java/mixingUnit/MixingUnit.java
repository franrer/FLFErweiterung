package mixingUnit;

import turrets.Turret;
import turrets.turretsWithFoam.TurretWithFoam;

public class MixingUnit implements IMixingUnit {
    private WaterTank waterTank;
    private FoamTank foamTank;


    public MixingUnit() {
        waterTank = new WaterTank(75, 45, 30);
        foamTank = new FoamTank(75, 45, 10);
    }

    public WaterTank getWaterTank() {
        return waterTank;
    }

    public FoamTank getFoamTank() {
        return foamTank;
    }

    @Override
    public int takeOut(Turret turret) {
        return waterTank.takeOut(turret.getTurretOutput());
    }

    @Override
    public int mixing(TurretWithFoam turret) {
        int foamAmount = (int) (((double) turret.getFoam() / 100) * turret.getTurretOutput());
        int waterAmount = turret.getTurretOutput() - foamAmount;
        int foam = foamTank.takeOut(foamAmount);
        int water = waterTank.takeOut(waterAmount);
        return water + foam;
    }

    @Override
    public int[] getTanksCapacity() {
        int[] result = new int[2];
        result[0] = waterTank.maxCapacity();
        result[1] = foamTank.maxCapacity();
        return result;
    }

    @Override
    public int[] getTanksFillState() {
        int[] result = new int[2];
        result[0] = waterTank.occupiedSpace();
        result[1] = foamTank.occupiedSpace();
        return result;
    }

    @Override
    public void fillTanks() {
        waterTank.fillToMax();
        foamTank.fillToMax();
    }
}
