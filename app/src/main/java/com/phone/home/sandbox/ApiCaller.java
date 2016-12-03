package com.phone.home.sandbox;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jetpackcat on 12/3/2016.
 */

public class ApiCaller {

    OkHttpClient client;
    String myUrl;

    public ApiCaller(String url) {
        myUrl = url;
        client = new OkHttpClient();
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getString() throws IOException {
        return run(myUrl);
    }

}
