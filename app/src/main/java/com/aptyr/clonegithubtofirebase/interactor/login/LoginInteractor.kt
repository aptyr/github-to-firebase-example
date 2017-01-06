package com.aptyr.clonegithubtofirebase.interactor.login

/**
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

import com.aptyr.clonegithubtofirebase.interactor.BaseInteractor
import com.aptyr.clonegithubtofirebase.model.RegisteredUser
import com.aptyr.clonegithubtofirebase.ui.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import rx.Observer

interface LoginInteractor : BaseInteractor{

    val googleApiClient : GoogleApiClient?

    fun activity(activity: LoginActivity)

    fun auth(account: GoogleSignInAccount)

    fun subscribe(subscriber: Observer<FirebaseUser?>)

    fun unsubscribe()

    fun getRegisteredUser(resultHandler: (RegisteredUser?) -> Unit, errorHandler: ((DatabaseError?) -> Unit)? = null)
}