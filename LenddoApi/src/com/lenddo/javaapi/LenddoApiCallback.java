package com.lenddo.javaapi;

import retrofit2.Response;

/**
 * Created by Joey Mar Antonio on 1/11/16.
 */
public interface LenddoApiCallback<T> {

    public void onResponse(T response);

    public void onFailure(Throwable t);
}
