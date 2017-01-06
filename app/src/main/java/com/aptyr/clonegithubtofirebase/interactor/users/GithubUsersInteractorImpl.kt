package com.aptyr.clonegithubtofirebase.interactor.users

/*
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.util.Log
import com.aptyr.clonegithubtofirebase.data.network.github.GithubAPI
import com.aptyr.clonegithubtofirebase.model.User
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GithubUsersInteractorImpl : GithubUsersInteractor {

    private val api = GithubAPI()


    override fun getUsers(since: Int, resultHandler: (List<User>) -> Unit, errorHandler: ((Throwable) -> Unit)?) {
        api.getUsers(since).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<User>> {

            override fun onError(e: Throwable?) {
                Log.e("mamuserow", "$e")
            }

            override fun onNext(t: List<User>?) {
                Log.d("mamuserow", "onNext")
                t?.let { resultHandler(it) }
            }

            override fun onCompleted() {
                Log.d("mamuserow", "complete")
            }

        })

    }

    override fun start() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun stop() {
        throw UnsupportedOperationException("not implemented")
    }
}