package teil2.task09;

import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;
import teil2.task01.*;

public interface ITesterVisitor {
    void visit(FloorSprayNozzle floorSprayNozzle);

    void visit(FrontTurret frontTurret);

    void visit(RoofTurret roofTurret);
}
