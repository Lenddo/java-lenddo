package com.lenddo.javaapi.services;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okio.Buffer;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;


import java.util.Collections;

/**
 * Created by Joey Mar Antonio on 1/14/16.
 */
public class ServiceGenerator {

    private static ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();

    private static OkHttpClient httpClient;

    private static Retrofit retrofit;

    private static Retrofit.Builder builder = new Retrofit.Builder()
                                                    .addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        httpClient = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();
        retrofit = builder.client(httpClient)
                            .baseUrl(baseUrl)
                            .build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
