package driveUnit;

import com.google.common.eventbus.Subscribe;
import inputs.switches.MotorSwitch;
import teil2.task02.ElectricMotorEvent;
import teil2.task02.Subscriber;
import teil2.task05.LoadingStation;

public class ElectricEngine extends Subscriber {

    private IPowerUnit powerUnit;
    private double consumption;   //12.5
    private boolean isStarted;


    public ElectricEngine() {
        this.isStarted = false;
        consumption = 12.5;
        this.powerUnit = null;
    }

    public ElectricEngine(MotorSwitch turnMotorSwitch) {
        super();
    }

    public IPowerUnit getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(IPowerUnit powerUnit) {
        this.powerUnit = powerUnit;
    }

    public double getConsumption() {
        return consumption;
    }

    public void start() {
        isStarted = true;
    }

    public void rotate(int kmh) {
        double toUse = consumption * kmh;
        powerUnit.usePower(((int) Math.ceil(toUse))); //freut sich über antwort und macht nichts
    }

    public void shutdown() {
        isStarted = false;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setOn(boolean on) {
        if (on) {
            start();
        } else {
            shutdown();
        }

    }

    @Subscribe
    public void receive(ElectricMotorEvent event) {
        setOn(!isStarted());
    }


}
