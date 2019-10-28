/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/15/19 10:29 AM
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NullOnEmptyConverterFactory extends Converter.Factory {


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody body) throws IOException {
                if (body.contentLength() == 0) return null;
                return delegate.convert(body);
            }
        };

    }
}
