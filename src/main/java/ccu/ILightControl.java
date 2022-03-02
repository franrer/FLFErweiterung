package ccu;

import lights.Light;

import java.util.ArrayList;

public interface ILightControl {

    void setLight(boolean onOff, ArrayList<Light> lights);

}
