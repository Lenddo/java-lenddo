package com.lenddo.javaapi.utils;

import com.lenddo.javaapi.models.APIError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by Joey Mar Antonio on 1/12/16.
 */
public class ErrorUtils {

    public static APIError parseError(Response<?> response, Retrofit retrofit) {
        Converter<ResponseBody, APIError> converter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}
