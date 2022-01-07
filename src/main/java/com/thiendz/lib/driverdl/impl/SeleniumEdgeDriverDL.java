package com.thiendz.lib.driverdl.impl;

import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;
import com.thiendz.lib.driverdl.utils.RequestUtils;
import com.thiendz.lib.driverdl.utils.Utils;
import com.thiendz.lib.driverdl.utils.enums.OsType;
import com.thiendz.lib.driverdl.utils.enums.SeleniumDriver;

import java.io.File;
import java.util.List;

public class SeleniumEdgeDriverDL implements SeleniumDriverDL {
    private static final String CMD_GET_EDGE_VERSION = "wmic datafile where 'name=\"C:\\\\Program Files (x86)\\\\Microsoft\\\\Edge\\\\Application\\\\msedge.exe\"'";
    private static final String REGEX_GET_EDGE_VERSION = "([0-9]{2,3}\\.[0-9]+\\.[0-9]+\\.[0-9]+)";
    private static final String URL_HOME_EDGE_DRIVER = "https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/";
    private static final String URL_DOWNLOAD_EDGE_DRIVER = "https://msedgedriver.azureedge.net/%s/edgedriver_%s.zip";

    String path;
    String osType;
    String version;

    public SeleniumEdgeDriverDL() {
        this.osType = OsType.OS_WINDOW_32.toString(SeleniumDriver.EDGE);
        this.path = Utils.getScriptDir();
    }

    @Override
    public void setPathSave(String path) {
        this.path = path;
    }

    @Override
    public void setOsType(OsType osType) {
        this.osType = osType.toString(SeleniumDriver.EDGE);
    }

    @Override
    public void init() throws Exception {
        String cmdResponse = Utils.cmdPrompt(CMD_GET_EDGE_VERSION);
        List<String> list = Utils.regex(REGEX_GET_EDGE_VERSION, cmdResponse);
        if (!list.isEmpty()) {
            this.version = list.get(0);
        }
        if (version == null)
            throw new Exception("get chrome version failure, support only window x32 & x64.");
    }

    @Override
    public void download() throws Exception {
        String body = RequestUtils.get(URL_HOME_EDGE_DRIVER);
        List<String> listVersions = Utils.regex(REGEX_GET_EDGE_VERSION, body);
        if (listVersions.isEmpty())
            throw new Exception("get list chrome version failure, maybe request failed.");
        String versionDevice = version.substring(0, version.indexOf("."));
        String versionMatch = null;
        for (String ver : listVersions) {
            String versionCode = ver.substring(0, version.indexOf("."));
            if (versionCode.equals(versionDevice)) {
                versionMatch = ver;
                break;
            }
        }
        if (versionMatch == null)
            throw new Exception("your chrome is old, update to the latest version.");
        String pathDownload = String.format(URL_DOWNLOAD_EDGE_DRIVER, versionMatch, osType);
        File file = new File(path + "/chromedriver_" + versionMatch + "_" + osType + ".zip");
        try {
            RequestUtils.download(file, pathDownload);
        } catch (Exception e) {
            throw new Exception("download driver failure, check osType.");
        }
        if (!Utils.unzip(file.getAbsolutePath(), path)) {
            throw new Exception("unzip failure, maybe download error.");
        }
        file.delete();
    }
}
