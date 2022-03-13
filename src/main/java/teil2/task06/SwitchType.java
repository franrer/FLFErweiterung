package teil2.task06;


public enum SwitchType {
    electroMotor(-1),
    floorSprayNozzles(-2),
    headLightsFront(3),
    warningLights(2),
    headLightsRoof(4),
    SideLights(1),
    BlueLights(0);
    private final int value;

    SwitchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
