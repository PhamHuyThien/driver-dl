package com.thiendz.lib.driverdl.example;

import com.thiendz.lib.driverdl.impl.SeleniumChromeDriverDL;
import com.thiendz.lib.driverdl.interf.SeleniumDriverDL;
import com.thiendz.lib.driverdl.utils.enums.OsType;

public class SeleniumDownloadExam {
    public static void main(String[] args) throws Exception {
        //Khởi tạo
        // Nếu sử dụng Edge có thể sử dụng new SeleniumEdgeDriverDL() hoặc SeleniumFirefoxDriverDL()
        SeleniumDriverDL seleniumChromeDriverDL = new SeleniumChromeDriverDL();
        //có thể chọn phiên bản tải về (mặc định WINDOW_32)
        seleniumChromeDriverDL.setOsType(OsType.OS_WINDOW_32);
        //có thể chọn thư mục lưu driver(mặc định là thư mục chứa code)
        seleniumChromeDriverDL.setPathSave("C:\\download");
        seleniumChromeDriverDL.init();
        seleniumChromeDriverDL.download();
    }
}
