/*package teil2.task01;
import mixingUnit.FoamTank;
import mixingUnit.WaterTank;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

public class Communicator {
    private final ArrayList<Object> Ports;
    private Object mixDevice;

    public Communicator(WaterTank water, FoamTank foam) {
        Ports = new ArrayList<>(Config.instance.maximumNumberOfEnginesPerWing);
        build();
    }
    public ArrayList<Object> getPorts() {
        return Ports;
    }
    @SuppressWarnings("unchecked")
    public void build() {
        try {
            // engines
            for (int i = 0; i < Config.instance.maximumNumberOfEnginesPerWing; i++) {
                URL[] urls = {new File(Config.instance.pathToEngineJavaArchive + "Configuration.jar").toURI().toURL()};
                URLClassLoader urlClassLoader = new URLClassLoader(urls, Communicator.class.getClassLoader());

                Class engineClass = Class.forName("Engine", true, urlClassLoader);
                Object engineInstance = engineClass.getMethod("getInstance").invoke(null);

                Object enginePort = engineClass.getDeclaredField("port").get(engineInstance);
                if (Config.instance.isDebug) {
                    MixType.instance.insert("CCU", "Port | " + enginePort.hashCode());
                }
                Ports.add(enginePort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    public void defill(int amount) {
        try {
            Method method = mixDevice.getClass().getDeclaredMethod("defill",int.class);
            method.invoke(this.mixDevice, amount);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setToNextMix() {
        try {
            Method method = mixDevice.getClass().getDeclaredMethod("setToNextMix");
            method.invoke(this.mixDevice);
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void WaterTank getWaterTank() {
        try {
            Method method = this.mixDevice.getClass().getDeclaredMethod("getWaterTank");
            return (WaterTank) method.invoke(this.mixDevice);
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public FoamTank getFoamTank() {
        try {
            Method method = mixDevice.getClass().getMethod("getFoamTank");
            Object returnValue =method.invoke(this.mixDevice);
            return (FoamTank) returnValue;
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


    public void defill(int amount) {
        try {
            Method method = mixDevice.getClass().getDeclaredMethod("defill",int.class);
            method.invoke(this.mixDevice, amount);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setToNextMix() {
        try {
            Method method = mixDevice.getClass().getDeclaredMethod("setToNextMix");
            method.invoke(this.mixDevice);
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public WaterTank getWaterTank() {
        try {
            Method method = this.mixDevice.getClass().getDeclaredMethod("getWaterTank");
            return (WaterTank) method.invoke(this.mixDevice);
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FoamTank getFoamTank() {
        try {
            Method method = mixDevice.getClass().getMethod("getFoamTank");
            Object returnValue =method.invoke(this.mixDevice);
            return (FoamTank) returnValue;
        } catch (NoSuchMethodException ne) {
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}*/
