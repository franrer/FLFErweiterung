package teil2.task09;

import turrets.FloorSprayNozzle;
import turrets.turretsWithFoam.FrontTurret;
import turrets.turretsWithFoam.RoofTurret;

public interface ITesterVisitor {
    void visit(FloorSprayNozzle floorSprayNozzle);
    void visit(FrontTurret frontTurret);
    void visit(RoofTurret roofTurret);
}
