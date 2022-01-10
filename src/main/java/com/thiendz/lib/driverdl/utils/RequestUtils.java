package com.thiendz.lib.driverdl.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestUtils {
    public static String get(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        Executor executor = Executor.newInstance(client);
        Request request = Request.Get(url);
        return executor.execute(request).returnContent().asString();
    }

    public static void download(File file, String urlDownload) throws IOException {
        Response response = Request.Get(urlDownload).execute();
        HttpResponse httpResponse = response.returnResponse();
        InputStream is = httpResponse.getEntity().getContent();
        FileOutputStream fos = new FileOutputStream(file);
        int inByte;
        while ((inByte = is.read()) != -1) {
            fos.write(inByte);
        }
        is.close();
        fos.close();
    }
}
