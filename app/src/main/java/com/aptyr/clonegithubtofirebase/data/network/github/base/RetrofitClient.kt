package com.aptyr.clonegithubtofirebase.data.network.github.base

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val builder = OkHttpClient.Builder().addInterceptor(interceptor)

        val client = builder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)

                .build()
        val service = retrofit.create(clazz)

        return service
    }
}
