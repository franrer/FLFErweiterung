package inputs.switches;

import cabin.operatorSection.Controlpanel;
import lights.Side;
import lights.Type;

public class LightSwitchArea extends LightSwitch {
    private Side side;

    public LightSwitchArea(Type type, Controlpanel controlpanel, Side side) {
        super(type, controlpanel);
        this.side = side;
    }

    // roof side und front  sind alles vom gleichen Typen deswegen hier eine abgeleitete klasse mit extra parameter Side
    public Side getSide() {
        return side;
    }
}
