public interface IMixingUnit {
    int takeOut(int turretOutput);

    int mixing(int turretFoam,int turretOutput);

    int[] getTanksCapacity();

    int[] getTanksFillState();

    void fillTanks();

}
