package com.aptyr.clonegithubtofirebase.data.network.github

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


import com.aptyr.clonegithubtofirebase.model.User

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface GithubService {

    @GET("users")
    fun getUsers(@Query("since") since: Int): Observable<List<User>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<User>

    companion object {

        val SERVICE_ENDPOINT = "https://api.github.com/"
    }


}
