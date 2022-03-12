package teil2.task05;

import driveUnit.electrical.BatteryManagement;

public class ThreeToOneAdapter extends ThreePinConnector implements IElectricLoader, IOnePinLoadable{
    private Pin outsidePin;
    public ThreeToOneAdapter(Pin pin1, Pin pin2, Pin pin3, BatteryManagement batteryManagement) {
        super(pin1, pin2, pin3, batteryManagement);
    }

    public void outsidePinConnect(Pin pin){
        outsidePin=pin;
        load();
    }

    @Override
    public void load(){
        pin1.setEnergyAmount((int)(0.3*outsidePin.getEnergyAmount()));
        pin2.setEnergyAmount((int)(0.3*outsidePin.getEnergyAmount()));
        pin3.setEnergyAmount((outsidePin.getEnergyAmount()-pin1.getEnergyAmount()- pin2.getEnergyAmount()));
        super.load();
    }
}
