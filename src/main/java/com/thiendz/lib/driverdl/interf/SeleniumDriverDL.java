package com.thiendz.lib.driverdl.interf;

import com.thiendz.lib.driverdl.utils.enums.OsType;

public interface SeleniumDriverDL {
    void setPathSave(String path);
    void setOsType(OsType osType);
    void init() throws Exception;
    void download() throws Exception;
}
