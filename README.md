# SeleniumDriverDL library

[SeleniumDriverDL](https://github.com/PhamHuyThien/driver-dl) là một thư viện hô trợ tự động tải [Selenium Driver](https://www.selenium.dev/documentation/webdriver/) phù hợp với phiên bản trình duyệt của bạn.

## Cài đặt

### Sử dụng Maven

```xml
<dependency>
    <groupId>com.thiendz.lib</groupId>
    <artifactId>driver-dl</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Sử dụng jar library

Jar SeleniumDriverDL có sẵn trong [releases github](https://github.com/PhamHuyThien/driver-dl/releases).

## Hướng dẫn sử dụng

```java
//Khởi tạo
// Nếu sử dụng Edge có thể sử dụng new SeleniumEdgeDriverDL()
SeleniumDriverDL seleniumChromeDriverDL = new SeleniumChromeDriverDL();
//có thể chọn phiên bản tải về (mặc định WINDOW_32)
seleniumChromeDriverDL.setOsType(OsType.OS_WINDOW_32);
//có thể chọn thư mục lưu driver(mặc định là thư mục chứa code)
seleniumChromeDriverDL.setPathSave("C:\\download");
seleniumEdgeDriverDL.init();
seleniumEdgeDriverDL.download();
```

## Ví dụ
[Nhấn vào đây](https://github.com/PhamHuyThien/driver-dl/blob/master/src/main/java/com/thiendz/lib/driverdl/example/SeleniumDownloadExam.java) để xem ví dụ.

## Lịch sử cập nhật
[Nhấn vào đây](https://github.com/PhamHuyThien/driver-dl/blob/master/change-log.md) để xem lịch sử cập nhật.

## Cộng đồng

Chúng tôi luôn tìm kiếm những người đóng góp, bạn có thể đóng góp, thắc mắc:

- [Tác giả](https://fb.com/thiendz.systemerror)