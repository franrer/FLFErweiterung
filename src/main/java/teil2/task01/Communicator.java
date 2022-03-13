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
    private Object mixDevice;

    public Communicator(WaterTank water, FoamTank foam) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Java\\jdk-17.0.2\\bin\\jarsigner", "-verify", "jar/Configuration.jar");
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            boolean isComponentAccepted = false;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("verified")) {
                    isComponentAccepted = true;
                }
            }
            if (!isComponentAccepted){
                throw new RuntimeException();
            }
            URL[] urls = {new File("jar\\Configuration.jar").toURI().toURL()};
            URLClassLoader load = new URLClassLoader(urls,Communicator.class.getClassLoader());
            Class mixdevice = Class.forName("MixDevice",true,load);
            Method method = mixdevice.getDeclaredMethod("getInstance",Object.class,Object.class);
            this.mixDevice = method.invoke(null,water,foam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
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
