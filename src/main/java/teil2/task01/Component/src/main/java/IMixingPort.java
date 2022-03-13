public interface IMixingPort {
    int portTakeOut(int  turretOutput);

    int portMixing(int turretFoam,int turretOutput);

    int[] portGetTanksCapacity();

    int[] portGetTanksFillState();

    void portFillTanks();

}
