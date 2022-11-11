package meltem.services.logging;

import meltem.enums.LogType;

public class Logger {
    public static void LogDebug(String msg) {
        System.out.println(String.format("[Debug] %s", msg));
    }
    public static void LogWarning(String msg) {
        System.out.println(String.format("[Warning] %s", msg));
    }
    public static void LogError(String msg) {
        System.out.println(String.format("[Error] %s", msg));
    }
    public static void Log(LogType logType, String msg) {
        System.out.println(String.format("[%s] %s", logType.toString(), msg));
    }
}

