package driveUnit;

public interface IPowerUnit {
    int getBatteryAmount();

    int getBatteryAmountInPercent();

    String usePower(int amount);

    String chargePower(int amount);
}
