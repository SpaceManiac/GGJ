package com.platymuus.ggj15;

/**
 * Simple facade for logging use.
 */
public final class LogWrapper {

    //private final Logger logger;
    private final String name;

    /*public LogWrapper(Logger logger) {
        this.logger = logger;
    }*/

    public LogWrapper(Class<?> clazz) {
        name = simpleName(clazz);
    }

    private String simpleName(Class<?> clazz) {
        String name = clazz.getSimpleName();
        if (!name.isEmpty()) {
            return name;
        } else {
            return simpleName(clazz.getEnclosingClass()) + "$" + clazz.getCanonicalName();
        }
    }

    public void info(String line) {
        System.err.println("[" + name + "/INFO] " + line);
    }

    public void warning(String line) {
        System.err.println("[" + name + "/WARNING] " + line);
    }

    public void error(String line) {
        System.err.println("[" + name + "/ERROR] " + line);
    }

    public void warning(String line, Throwable throwable) {
        warning(line);
        throwable.printStackTrace();
    }

    public void error(String line, Throwable throwable) {
        error(line);
        throwable.printStackTrace();
    }

}
