package com.aptyr.clonegithubtofirebase.data.network.firebase.auth

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

import com.aptyr.clonegithubtofirebase.view.login.LoginActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth

class AuthProvider private constructor(val activity: LoginActivity, listener: GoogleApiClient.OnConnectionFailedListener?) {


    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("OAUTH CLIENT ID")
            .requestEmail()
            .build()

    val googleApiClient = GoogleApiClient.Builder(activity)
            .enableAutoManage(activity, listener)
            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
            .build()

    val firebaseAuth = FirebaseAuth.getInstance()

    fun auth(){
    }


    private constructor(builder: Builder) : this(builder.activity, builder.listener)

    class Builder(val activity: LoginActivity) {

        var listener: GoogleApiClient.OnConnectionFailedListener? = null
            private set

        fun listener(listener: GoogleApiClient.OnConnectionFailedListener?) : Builder{
            this.listener = listener
            return this
        }

        fun build() = AuthProvider(this)
    }
}