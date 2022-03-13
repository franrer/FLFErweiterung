package teil2.task01;

public enum Config {
    instance;

    public final boolean isDebug = false;

    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");
    public final String pathToEngineJavaArchive = userDirectory + fileSeparator + "engine" + fileSeparator + "jar" + fileSeparator;

    public final String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public final String databaseFile = dataDirectory + "flight_recorder_a350.db";

    public final int maximumNumberOfEnginesPerWing = 1;

    public final int taxiTimeInSeconds = 5;
    public final int taxiEngineIncreaseRPMPerSeconds = 50;

    public final int takeOffTimeInSeconds = 14;
    public final int takeOffEngineIncreaseRPMPerSecond = 250;
}
