package com.thiendz.lib.driverdl.example;

import com.thiendz.lib.driverdl.impl.SeleniumChromeDriverDL;
import com.thiendz.lib.driverdl.impl.SeleniumEdgeDriverDL;
import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;

public class SeleniumDownloadExam {
    public static void main(String[] args) throws Exception {
        SeleniumDriverDL seleniumChromeDriverDL = new SeleniumChromeDriverDL();
        seleniumChromeDriverDL.init();
        seleniumChromeDriverDL.download();

        SeleniumDriverDL seleniumEdgeDriverDL = new SeleniumEdgeDriverDL();
        seleniumEdgeDriverDL.init();
        seleniumEdgeDriverDL.download();
    }
}
