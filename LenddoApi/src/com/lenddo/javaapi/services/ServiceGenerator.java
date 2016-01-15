package com.lenddo.javaapi.services;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Joey Mar Antonio on 1/14/16.
 */
public class ServiceGenerator {

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder = new Retrofit.Builder()
                                                    .addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = builder.client(httpClient)
                            .baseUrl(baseUrl)
                            .build();
        return retrofit.create(serviceClass);
    }


}
