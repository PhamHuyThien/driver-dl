package com.thiendz.lib.driverdl.utils.enums;

public enum OsType {
    OS_LINUX_64, OS_LINUX_32, OS_WINDOW_64, OS_WINDOW_32, OS_MAC, OS_MAC_M1, OS_ARM_64;

    public String toString(SeleniumDriver seleniumDriver) {
        if (seleniumDriver == SeleniumDriver.CHROME) {
            switch (this) {
                case OS_MAC:
                    return "mac";
                case OS_LINUX_32:
                    return "linux32";
                case OS_LINUX_64:
                    return "linux64";
                case OS_MAC_M1:
                    return "OS_MAC_M1";
                case OS_WINDOW_32:
                    return "win32";
                case OS_WINDOW_64:
                    return "win64";
            }
        } else if (seleniumDriver == SeleniumDriver.EDGE) {
            switch (this) {
                case OS_MAC:
                    return "mac64";
                case OS_LINUX_64:
                    return "linux64";
                case OS_ARM_64:
                    return "arm64";
                case OS_WINDOW_32:
                    return "win32";
                case OS_WINDOW_64:
                    return "win64";
            }
        }
        return "";
    }
}
