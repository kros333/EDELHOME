package com.EDELHOME;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTApiClient {

    private static Retrofit retrofit;

    // create http
    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000,TimeUnit.SECONDS).build();

    // Single
    public static Retrofit getClient() {

        if (retrofit == null) {

            // retrofit singleton class object
            retrofit = new Retrofit.Builder()
                    // the base url don't work. It's for demo purpose.
                    .baseUrl("https://24e8-213-208-174-203.eu.ngrok.io/appusers/").client(client)
                    .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        }
        return retrofit;
    }

}
