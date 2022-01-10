package com.thiendz.lib.driverdl.impl;

import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;
import com.thiendz.lib.driverdl.utils.RequestUtils;
import com.thiendz.lib.driverdl.utils.Utils;
import com.thiendz.lib.driverdl.utils.enums.OsType;
import com.thiendz.lib.driverdl.utils.enums.SeleniumDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumFirefoxDriverDL implements SeleniumDriverDL {
    private static final String URL_HOME_FIREFOX_DRIVER = "https://github.com/mozilla/geckodriver/releases";
    String osType;
    String path;

    public SeleniumFirefoxDriverDL() {
        this.osType = OsType.OS_WINDOW_32.toString(SeleniumDriver.FIREFOX);
        this.path = Utils.getScriptDir();
    }

    @Override
    public void setPathSave(String path) {
        this.path = path;
    }

    @Override
    public void setOsType(OsType osType) {
        this.osType = OsType.OS_WINDOW_32.toString(SeleniumDriver.FIREFOX);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void download() throws Exception {
        String body = RequestUtils.get(URL_HOME_FIREFOX_DRIVER);
        Document document = Jsoup.parse(body);
        Elements elmLinkDownload = document.select("a[rel='nofollow']");
        List<String> firefoxDrivers = elmLinkDownload.stream().filter(element -> {
            String path = element.attr("href");
            return (path.endsWith(".gz") || path.endsWith(".zip")) && path.contains("releases");
        }).map(element -> {
            String path = element.attr("href");
            return "https://github.com/" + path;
        }).collect(Collectors.toList());
        String pathDownload = null;
        for (String s : firefoxDrivers) {
            if (s.contains(osType)) {
                pathDownload = s;
                break;
            }
        }
        if (pathDownload == null)
            throw new Exception("your firefox is old, update to the latest version.");
        File file = new File(path + "\\" + pathDownload.substring(pathDownload.lastIndexOf("/") + 1));
        try {
            RequestUtils.download(file, pathDownload);
        } catch (Exception e) {
            throw new Exception("download driver failure, check osType.");
        }
        if (!Utils.unzip(file.getAbsolutePath(), path))
            throw new Exception("unzip failure, maybe download error.");
        if (!file.delete())
            throw new Exception("delete file zip error.");
    }

}
