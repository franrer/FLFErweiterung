package turrets;

import ccu.CCU;

public class FloorSprayNozzle extends Turret {

    CCU ccu;

    public FloorSprayNozzle(CCU ccu) {
        super(100);
        this.ccu = ccu;
    }


}
