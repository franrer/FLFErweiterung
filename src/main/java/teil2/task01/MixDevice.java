package teil2.task01;
import java.lang.reflect.Method;

public class MixDevice {
    private int mixType;
    private Object water;
    private Object foam;

    public MixDevice(Object water,Object foam) {
        this.mixType = 0;
        this.water = water;
        this.foam = foam;
    }
    public void defill(int amount)  {
        try {
            double foamV = amount*((this.mixType)/ 100);
            double waterV = amount-foamV;
            Method waterTakeout = this.water.getClass().getDeclaredMethod("takeOut",Float.class);
            waterTakeout.invoke(null,(Float.valueOf((float) waterV)));
            Method foamTakeout = this.foam.getClass().getDeclaredMethod("takeOut",Float.class);
            foamTakeout.invoke(null,(Float.valueOf((float) foamV)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public void setToNextMix() {
        switch(this.mixType){
            case 0 -> this.mixType = 3;
            case 3 -> this.mixType = 5;
            case 5 -> this.mixType = 10;
            case 10 -> this.mixType = 0;
        }
    }
    public Object getWaterTank() {
        return this.water;
    }
    public Object getFoamTank() {
        return this.foam;
    }
}
