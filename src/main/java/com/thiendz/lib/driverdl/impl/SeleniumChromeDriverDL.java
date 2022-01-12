package com.thiendz.lib.driverdl.impl;

import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;
import com.thiendz.lib.driverdl.utils.RequestUtils;
import com.thiendz.lib.driverdl.utils.Utils;
import com.thiendz.lib.driverdl.utils.enums.OsType;
import com.thiendz.lib.driverdl.utils.enums.SeleniumDriver;

import java.io.File;
import java.util.List;

public class SeleniumChromeDriverDL implements SeleniumDriverDL {

    private static final String CMD_GET_CHROME_VERSION = "reg query \"HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon\" /v version";
    private static final String REGEX_GET_CHROME_VERSION = "([0-9]{2,3}\\.[0-9]+\\.[0-9]+\\.[0-9]+)";
    private static final String URL_HOME_CHROME_DRIVER = "https://chromedriver.chromium.org/downloads";
    private static final String URL_DOWNLOAD_CHROME_DRIVER = "https://chromedriver.storage.googleapis.com/%s/chromedriver_%s.zip";

    String path;
    String osType;
    String version;

    public SeleniumChromeDriverDL() {
        this.osType = OsType.OS_WINDOW_32.toString(SeleniumDriver.CHROME);
        this.path = Utils.getScriptDir();
    }

    @Override
    public void setPathSave(String path) {
        this.path = path;
    }

    @Override
    public void setOsType(OsType osType) {
        this.osType = osType.toString(SeleniumDriver.CHROME);
    }

    @Override
    public void init() throws Exception {
        String cmdResponse = Utils.cmdPrompt(CMD_GET_CHROME_VERSION);
        List<String> list = Utils.regex(REGEX_GET_CHROME_VERSION, cmdResponse);
        if (!list.isEmpty()) {
            this.version = list.get(0);
        }
        if (version == null)
            throw new Exception("get chrome version failure, support only window x32 & x64.");
    }

    @Override
    public void download() throws Exception {
        String body = RequestUtils.get(URL_HOME_CHROME_DRIVER);
        List<String> listVersions = Utils.regex(REGEX_GET_CHROME_VERSION, body);
        if (listVersions.isEmpty()) {
            throw new Exception("get list chrome version failure, maybe request failed.");
        }
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
        String pathDownload = String.format(URL_DOWNLOAD_CHROME_DRIVER, versionMatch, osType);
        File file = new File(path + "/chromedriver_" + versionMatch + "_" + osType + ".zip");
        System.out.println(pathDownload);
        try {
            RequestUtils.download(file, pathDownload);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
        if (!Utils.unzip(file.getAbsolutePath(), path))
            throw new Exception("unzip failure, maybe download error.");
        if (!file.delete())
            throw new Exception("delete file zip error.");
    }
}
