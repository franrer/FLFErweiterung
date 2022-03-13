package teil2.task01;

import mixingUnit.IMixingUnit;
import turrets.Turret;
import turrets.turretsWithFoam.TurretWithFoam;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Communicator implements IMixingUnit {

    private Object port;

    public Communicator() {
        port = buildPort();
    }

    public Object getPort() {
        return port;
    }

    @Override
    public int takeOut(Turret turret) {
        int output = 0;
        try {
            Method method = port.getClass().getMethod(PortConfiguration.instance.nameOfTakeOutMethode, int.class);
            output = (int) method.invoke(port, turret.getTurretOutput());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public int mixing(TurretWithFoam turret) {
        int output = 0;
        try {
            Method method = port.getClass().getMethod(PortConfiguration.instance.nameOfMixingMethode, int.class, int.class);
            output = (int) method.invoke(port, turret.getFoam(), turret.getTurretOutput());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public int[] getTanksCapacity() {
        int[] output = null;
        try {
            Method method = port.getClass().getMethod(PortConfiguration.instance.nameOfGetTanksCapacityMethode);
            output = (int[]) method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public int[] getTanksFillState() {
        int[] output = null;
        try {
            Method method = port.getClass().getMethod(PortConfiguration.instance.nameOfGetTanksFillStateMethode);
            output = (int[]) method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public void fillTanks() {
        try {
            Method method = port.getClass().getMethod(PortConfiguration.instance.nameOfTFillTanksMethode);
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Object buildPort() {
        Object port = null;
        try {
            URL[] urls = {new File(PortConfiguration.instance.pathToJar).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Communicator.class.getClassLoader());

            Class cryptClass = Class.forName(PortConfiguration.instance.nameOfPortClass, true, urlClassLoader);

            Constructor<?> cryptConstructor = cryptClass.getConstructor();
            Object cryptManager = cryptConstructor.newInstance();
            Method methode = cryptClass.getMethod("getPort");
            port = methode.invoke(cryptManager);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return port;
    }
}
