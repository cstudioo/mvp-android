package com.cstudioo.mvpdemologin.webservice;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * manage api response errors.
 */
public class ErrorUtils {
    public static APIError parseError(Response<?> response) {
        ServiceWrapper serviceWrapper = new ServiceWrapper(null);
        Converter<ResponseBody, APIError> converter =
                serviceWrapper.getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error = new APIError();
        error.setStatus_code(010);

        try {
//            Log.e("ErrorUtils", "Service Error Body : " + response.body().toString());
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            APIError networkError = new APIError();
            if (response.code() == 404) {
                networkError.setStatus_code(404);
                networkError.setMessage("Page Not Found");
                return networkError;
            } else if (response.code() == 403) {
                networkError.setStatus_code(403);
                networkError.setMessage("Problem while fetching data from server. Please try again later.");
                return networkError;
            } else if (e.getCause() instanceof SocketTimeoutException) {
                networkError.setStatus_code(000);
                networkError.setMessage("Network Error! \\nPoor connection or connection lost.");
                return networkError;
            } else if (e.getCause() instanceof UnknownHostException || e.getCause() instanceof ConnectException) {
                networkError.setStatus_code(000);
                networkError.setMessage("No internet connection.");
                return networkError;
            } else {
                networkError.setStatus_code(000);
                networkError.setMessage("Problem while fetching data from server. Please try again later.");
                return networkError;
            }
        }

        return error;
    }
}
