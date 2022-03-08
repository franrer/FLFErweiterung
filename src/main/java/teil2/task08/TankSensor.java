package teil2.task08;

import java.util.ArrayList;

public class TankSensor {
    private final ArrayList<ITankSensorListener> listenerList;

    public TankSensor() {
        this.listenerList = new ArrayList<>();
    }

    public void addListener(ITankSensorListener listener) {
        listenerList.add(listener);
    }

    public void removeListener(ITankSensorListener listener) {
        listenerList.remove(listener);
    }

    public void moreThanHalfRemaining() {
        for (ITankSensorListener listener : listenerList) {
            listener.moreThanHalf();
        }
    }

    public void lessThanHalfRemaining() {
        for (ITankSensorListener listener : listenerList) {
            listener.lessThanHalf();
        }
    }

    public void lessThanQuarterRemaining() {
        for (ITankSensorListener listener : listenerList) {
            listener.lessThanQuarter();
        }
    }

    public void lessThanTenPercentRemaining() {
        for (ITankSensorListener listener : listenerList) {
            listener.lessThanTenPercent();
        }
    }
}
