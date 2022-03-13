public class MixingUnit implements IMixingUnit {
    private WaterTank waterTank;
    private FoamTank foamTank;
    public Port port;

    public MixingUnit() {
        waterTank = new WaterTank(75, 45, 30);
        foamTank = new FoamTank(75, 45, 10);
        port=new Port();
    }

    public Port getPort() {
        return port;
    }

    @Override
    public int takeOut(int turretOutput) {
        return waterTank.takeOut(turretOutput);
    }


    @Override
    public int mixing(int turretFoam,int turretOutput) {
        int foamAmount = (int) (((double) turretFoam / 100) * turretOutput);
        int waterAmount = turretOutput - foamAmount;
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

    public class Port implements IMixingPort {



        @Override
        public int portTakeOut(int turretOutput) {
            return takeOut(turretOutput);
        }


        @Override
        public int portMixing(int turretFoam, int turretOutput) {
            return mixing(turretFoam,turretOutput);
        }


        @Override
        public int[] portGetTanksCapacity() {
            return getTanksCapacity();
        }


        @Override
        public int[] portGetTanksFillState() {
            return getTanksFillState();
        }


        @Override
        public void portFillTanks() {
            fillTanks();
        }

    }

}
