package turrets;

import ccu.CCU;
import teil2.task09.ITesterVisitor;
import teil2.task09.IUnitToTest;

public class FloorSprayNozzle extends Turret implements IUnitToTest {

    CCU ccu;

    public FloorSprayNozzle(CCU ccu) {
        super(100);
        this.ccu = ccu;
    }

    @Override
    public void accept(ITesterVisitor visitor) {
        visitor.visit(this);
    }
}
