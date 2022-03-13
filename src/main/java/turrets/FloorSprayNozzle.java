package turrets;

import com.google.common.eventbus.Subscribe;
import teil2.task02.*;
import turrets.turretsWithFoam.*;
import ccu.CCU;
import teil2.task09.ITesterVisitor;
import teil2.task09.IUnitToTest;

public class FloorSprayNozzle extends Turret implements IUnitToTest {


    private CCU ccu;

    public FloorSprayNozzle(CCU ccu) {
        super(100);
        this.ccu=ccu;
    }

    @Override
    public void accept(ITesterVisitor visitor) {
        visitor.visit(this);
    }
    @Subscribe
    public void recieve(SelfProtectionEvent event) {
        onOff();
        if (isActive()) {
            spray(ccu.getMixingUnit().takeOut(this));
        }

    }

}