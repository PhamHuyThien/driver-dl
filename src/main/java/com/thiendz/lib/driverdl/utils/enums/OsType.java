package com.thiendz.lib.driverdl.utils.enums;

public enum OsType {
    OS_LINUX_64, OS_LINUX_32, OS_WINDOW_64, OS_WINDOW_32, OS_MAC, OS_MAC_M1, OS_MAC_64, OS_ARM_64;

    public String toString(SeleniumDriver seleniumDriver) {
        switch (this) {
            case OS_MAC:
                switch (seleniumDriver) {
                    case EDGE:
                        return "mac64";
                    case CHROME:
                        return "mac";
                    case FIREFOX:
                        return "-macos.";
                }
                break;
            case OS_LINUX_64:
                switch (seleniumDriver) {
                    case EDGE:
                    case CHROME:
                    case FIREFOX:
                        return "linux64";
                }
                break;
            case OS_ARM_64:
                return "arm64";
            case OS_WINDOW_32:
                switch (seleniumDriver) {
                    case EDGE:
                    case CHROME:
                    case FIREFOX:
                        return "win32";
                }
                break;
            case OS_WINDOW_64:
                switch (seleniumDriver) {
                    case EDGE:
                    case CHROME:
                    case FIREFOX:
                        return "win64";
                }
                break;
            case OS_LINUX_32:
                return "linux32";
            case OS_MAC_M1:
                return "mac64_m1";
            case OS_MAC_64:
                return "macos-aarch64";
        }
        return "";
    }
}
