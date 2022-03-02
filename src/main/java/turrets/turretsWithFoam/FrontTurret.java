package turrets.turretsWithFoam;

public class FrontTurret extends TurretWithFoam {
    private int position;

    public FrontTurret() {
        super(500);
        this.position = 0;
    }


    @Override
    public void increaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 1000;
            case 1000 -> turretWater = 1500;
            case 1500 -> turretWater = 2000;
            case 2000 -> turretWater = 2500;
            case 2500 -> turretWater = 3000;
            case 3000 -> turretWater = 3500;
            case 3500 -> turretWater = 500;

        }
    }

    @Override
    public void decreaseWater() {
        switch (turretWater) {
            case 500 -> turretWater = 3500;
            case 1000 -> turretWater = 500;
            case 1500 -> turretWater = 1000;
            case 2000 -> turretWater = 1500;
            case 2500 -> turretWater = 2000;
            case 3000 -> turretWater = 2500;
            case 3500 -> turretWater = 3000;
        }
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int getStage() {
        return switch (turretWater) {
            case 500 -> 1;
            case 1000 -> 2;
            case 1500 -> 3;
            case 2000 -> 4;
            case 2500 -> 5;
            case 3000 -> 6;
            case 3500 -> 7;
            default -> 0;
        };
    }

    @Override
    public void onOff() {
        if (isActive) {
            position = 90;
        } else {
            position = 0;
        }
        super.onOff();
    }
}
