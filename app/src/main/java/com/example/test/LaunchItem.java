package com.example.test;

public class LaunchItem {
    private boolean isLaunchble = false;
    private String packageName;
    private String className;

    public boolean isLaunchble() {
        return isLaunchble;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public LaunchItem(boolean isLaunchble, String packageName, String className) {
        this.isLaunchble = isLaunchble;
        this.packageName = packageName;
        this.className = className;
    }

}
