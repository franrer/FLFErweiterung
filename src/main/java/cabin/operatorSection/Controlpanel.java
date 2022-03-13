package cabin.operatorSection;

import inputs.switches.*;
import lights.Side;
import lights.Type;
import teil2.task08.TankLed;

public class Controlpanel {

    private LightSwitch[] lightSwitches;
    private SelfProtection selfProtection;
    private MotorSwitch motorSwitch;
    private OperatorSection operatorSection;
    private TankLed waterLed;
    private TankLed foamLed;

    public Controlpanel() {

    }

    public LightSwitch getBlueLightSwitch() {
        for (LightSwitch l : lightSwitches) {
            if (l.getType() == Type.BLUELIGHT) {
                return l;
            }
        }
        return null;
    }

    public LightSwitch getWarningLightSwitch() {
        for (LightSwitch l : lightSwitches) {
            if (l.getType() == Type.WARNINGLIGHT) {
                return l;
            }
        }
        return null;
    }

    public LightSwitchArea getFrontLightSwitch() {
        for (LightSwitch l : lightSwitches) {
            if (l instanceof LightSwitchArea a)
                if (a.getSide() == Side.FRONT) {
                    return a;
                }
        }
        return null;
    }

    public LightSwitchArea getSideLightSwitch() {
        for (LightSwitch l : lightSwitches) {
            if (l instanceof LightSwitchArea a)
                if (a.getSide() == Side.SIDE) {
                    return a;
                }
        }
        return null;
    }

    public LightSwitchArea getRoofLightSwitch() {
        for (LightSwitch l : lightSwitches) {
            if (l instanceof LightSwitchArea a)
                if (a.getSide() == Side.ROOF) {
                    return a;
                }
        }
        return null;
    }

    public OperatorSection getOperatorSection() {
        return operatorSection;
    }

    public void setOperatorSection(OperatorSection operatorSection) {
        this.operatorSection = operatorSection;
    }

    public void setLightSwitches(LightSwitch[] lightSwitches) {
        this.lightSwitches = lightSwitches;
    }

    public SelfProtection getSelfProtection() {
        return selfProtection;
    }

    public void setSelfProtection(SelfProtection selfProtection) {
        this.selfProtection = selfProtection;
    }

    public MotorSwitch getMotorSwitch() {
        return motorSwitch;
    }

    public void setMotorSwitch(MotorSwitch motorSwitch) {
        this.motorSwitch = motorSwitch;
    }

    public TankLed getWaterLed() {
        return waterLed;
    }

    public void setWaterLed(TankLed waterLed) {
        this.waterLed = waterLed;
    }

    public TankLed getFoamLed() {
        return foamLed;
    }

    public void setFoamLed(TankLed foamLed) {
        this.foamLed = foamLed;
    }

    public void turnSwitch(Switch s) {
        operatorSection.turnSwitch(s);
    }
}
