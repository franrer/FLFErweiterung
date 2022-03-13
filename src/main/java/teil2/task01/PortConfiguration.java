package teil2.task01;

public enum PortConfiguration {
    instance;
    public final String nameOfJarFolder = "Component";
    public final String nameOfJar = "signedjar";
    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");
    public final String pathToComponent = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java"+fileSeparator + "teil2"+fileSeparator + "task01";
    public final String pathToJarFolder = pathToComponent + fileSeparator + nameOfJarFolder + fileSeparator + "jar" + fileSeparator;

    public final String pathToJar = pathToJarFolder + nameOfJar + ".jar";
    public final String nameOfPortClass = "MixingUnit";

    public final String nameOfTakeOutMethode = "portTakeOut";
    public final String nameOfMixingMethode = "portMixing";
    public final String nameOfGetTanksCapacityMethode = "portGetTanksCapacity";
    public final String nameOfGetTanksFillStateMethode = "portGetTanksFillState";
    public final String nameOfTFillTanksMethode = "portFillTanks";


}
