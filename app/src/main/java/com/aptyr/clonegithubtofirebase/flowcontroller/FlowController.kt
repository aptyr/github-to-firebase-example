package com.aptyr.clonegithubtofirebase.flowcontroller

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

import android.content.Context
import android.content.Intent
import com.aptyr.clonegithubtofirebase.view.login.LoginActivity
import com.aptyr.clonegithubtofirebase.view.users.FirebaseUsersActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient

class FlowController private constructor() {

    val RC_GOOGLE_SIGN_IN = 1

    private object Holder {
        val INSTANCE = FlowController()
    }

    companion object {
        val instance: FlowController by lazy { Holder.INSTANCE }
    }

    fun googleSignInView(context: LoginActivity, googleApiClient: GoogleApiClient?) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        context.startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    fun firebaseUsersView(context: Context) {
        val intent = Intent(context, FirebaseUsersActivity::class.java)
        context.startActivity(intent)
    }

}