package com.aptyr.clonegithubtofirebase.data.network.github

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.aptyr.clonegithubtofirebase.data.network.github.base.RetrofitClient
import com.aptyr.clonegithubtofirebase.model.User

import rx.Observable


class GithubAPI {

    private val restService: GithubService = RetrofitClient.createRetrofitService(GithubService::class.java, GithubService.SERVICE_ENDPOINT)

    fun getUsers(since: Int): Observable<List<User>> {
        return restService.getUsers(since)
    }

    fun getUser(login: String): Observable<User> {
        return restService.getUser(login)
    }


}
