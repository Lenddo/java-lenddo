package com.lenddo.javaapi;

/**
 * Created by Joey Mar Antonio on 1/11/16.
 */
public interface LenddoApiCallback<T> {

    public void onResponse(T response);

    public void onFailure(Throwable t);

    public void onError(String errormessage);
}
