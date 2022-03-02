package ccu;

import cabin.driverSection.IDisplay;
import inputs.driverInputs.IPedal;

public interface IDriveUnitControl {
    void stepOn(IPedal pedal);

    void steer(int degree);

    String show(IDisplay display);
}
