package com.dtdev.aparat.models;

import java.util.Map;

public interface IResponseListener<T> {

    void onSuccess(T responseMassage);

    void onFailure(String errorResponseMassage);


}
