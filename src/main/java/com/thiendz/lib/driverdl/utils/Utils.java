package com.thiendz.lib.driverdl.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Utils {
    public static boolean unzip(String inputFile, String outputFolder) {
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        byte[] buffer = new byte[1024];
        ZipInputStream zipIs = null;
        try {
            zipIs = new ZipInputStream(new FileInputStream(inputFile));
            ZipEntry entry = null;
            while ((entry = zipIs.getNextEntry()) != null) {
                String entryName = entry.getName();
                String outFileName = outputFolder + File.separator + entryName;
                System.out.println("Unzip: " + outFileName);
                if (entry.isDirectory()) {
                    new File(outFileName).mkdirs();
                } else {
                    FileOutputStream fos = new FileOutputStream(outFileName);
                    int len;
                    while ((len = zipIs.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert zipIs != null;
                zipIs.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static List<String> regex(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        List<String> listResult = new ArrayList<>();
        while (m.find()) {
            listResult.add(m.group());
        }
        return listResult;
    }

    public static String cmdPrompt(String cmd) {
        try {
            Process pr = Runtime.getRuntime().exec(cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            buf.readLine();
            buf.readLine();
            return buf.readLine().trim();
        } catch (IOException | InterruptedException ignored) {
        }
        return null;
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getScriptDir() {
        return System.getProperty("user.dir");
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }
}
