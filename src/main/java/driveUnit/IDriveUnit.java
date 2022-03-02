package driveUnit;

public interface IDriveUnit {
    void changeSpeed(int amount);

    void drive();

    void turn(int degree);

    int getSpeed();

    int getDirection();

    boolean getMotorState();

    void startEngine();

    void shutdownEngine();

}
